(ns server.handler
  (:require [compojure.core :refer [defroutes context GET POST]]
            [compojure.route :as route]
            [server.database :as db]))

(def head {"Content-Type" "application/json"})

(def resp-ok
  {:status 200
   :headers head
   :body "{\"message\":\"ok\"}"})

(def resp-404
  {:status 404
   :headers head
   :body "{\"message\":\"ng\"}"})

(defn member-exists? [email]
  (= 1 (count (db/select-member email))))

(defn api-signup [req]
  (if (member-exists? (:email req))
    resp-404
    (do
      (db/insert-member (:email req) (:password req))
      resp-ok)))

(defn api-signin [req]
  (if (member-exists? (:email req))
    resp-ok
    resp-404))

(defroutes handler
  (context "/api/v1" _
           (POST "/signup" req api-signup)
           (POST "/signin" req api-signin)
           )
  (route/not-found "{\"message\":\"api not found\"}"))
