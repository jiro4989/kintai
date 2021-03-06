(ns server.handler
  (:require [compojure.core :refer [defroutes context GET POST]]
            [compojure.route :as route]
            [server.database :as db]
            [ring.util.response :as res]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.json :refer [wrap-json-response]]
            [buddy.auth.accessrules :as baa]
            [buddy.auth.backends :as bab]
            [buddy.auth.middleware :as bam]))

(defn any-access [_]
  (baa/success))

(defn member-access [{:keys [id]}]
  (baa/success))

; 後で実装
; (defn member-access [{:keys [id]}]
;   (if id
;     (baa/success)
;     (baa/error)))

(def head {"Content-Type" "application/json"})

(def resp-ok
  (res/response {:message "ok"}))

(def resp-bad
  (res/bad-request {:message "bad request"}))

(defn member-exists? [email]
  (= 1 (count (db/select-member email))))

(defn api-signup [req]
  (if (member-exists? (:email req))
    resp-bad
    (do
      ; (println req)
      (println "session is " (:session req))
      (db/insert-member (:email req) (:password req))
      resp-ok)))

(defn api-signin [req]
  (if-let [member (db/select-member (:email req))]
    (-> resp-ok
        (assoc :session (vary-meta (assoc (:session req) :id (:email member))
                                   assoc :recreate true)))
    resp-bad))

(defn api-members [req]
  (res/response {:members (db/select-members)}))

(defn session-authfn
  [id]
  id)

(def rules
  [
   ; サインアップ・サインインは認証不要
   {:uris ["/api/v1/signup" "/api/v1/signin"]
    :handler any-access}
   ; それ以外のURLには認証必須
   {:pattern #"/api/v1/.*"
    :handler member-access
    :on-error (fn [_ _] resp-bad)}
   ])

(defroutes base-handler
  (context "/api/v1" _
           (POST "/signup" req api-signup)
           (POST "/signin" req api-signin)
           (GET "/members" req api-members)
           )
  (route/not-found {:message "api not found"}))

(def handler
  (-> base-handler
      wrap-params
      wrap-session
      wrap-json-response
      (baa/wrap-access-rules {:rules rules})
      (bam/wrap-authentication (bab/session {:authfn session-authfn}))))
