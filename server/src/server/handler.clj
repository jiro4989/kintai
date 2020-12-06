(ns server.handler
  (:require [compojure.core :refer [defroutes context GET POST]]
            [compojure.route :as route]
            [server.database :as db]
            [ring.util.response :as res]
            [buddy.auth.accessrules :as baa]
            [buddy.auth.backends :as bab]
            [buddy.auth.middleware :as bam]))

(defn any-access [_]
  (baa/success))

(defn member-access [{:keys [id]}]
  (if id
    (baa/success)
    (baa/error)))

(def head {"Content-Type" "application/json"})

(def resp-ok
  {:status 200
   :headers head
   :body "{\"message\":\"ok\"}"})

(defn member-exists? [email]
  (= 1 (count (db/select-member email))))

(defn api-signup [req]
  (if (member-exists? (:email req))
    (res/bad-request "ng")
    (do
      ; (println req)
      (println "session is " (:session req))
      (db/insert-member (:email req) (:password req))
      resp-ok)))

(defn api-signin [req]
  (if-let [member (db/select-memger (:email req))]
    (-> resp-ok
        (assoc :session (vary-meta (assoc session :id (:email member))
                                   assoc :recreate true)))
    (res/bad-request "ng")))

(defroutes handler
  (context "/api/v1" _
           (POST "/signup" req api-signup)
           (POST "/signin" req api-signin)
           )
  (route/not-found "{\"message\":\"api not found\"}"))
