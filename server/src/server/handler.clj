(ns server.handler
  (:require [compojure.core :refer [defroutes context GET]]
            [compojure.route :as route]))

(defn api-signup [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "{\"signup ok\"}"})

(defn api-signin [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "{\"signin ok\"}"})

(defroutes handler
  (GET "/api/v1/signup" req api-signup)
  (GET "/api/v1/signin" req api-signin)
  (route/not-found "{\"message\":\"api not found\"}"))
