(ns server.handler
  (:require [compojure.core :refer [defroutes context GET POST]]
            [compojure.route :as route]))

(defn api-signup [req]
  (do
    (println req)
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body "{\"signin ok\"}"}))

(defn api-signin [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "{\"signin ok\"}"})

(defroutes handler
  (context "/api/v1" _
           (POST "/signup" req api-signup)
           (POST "/signin" req api-signin)
           )
  (route/not-found "{\"message\":\"api not found\"}"))
