(ns server.core
  (:use ring.adapter.jetty)
  (:require [compojure.core :refer [defroutes context GET]]
            [compojure.route :as route]
            [server.handler :refer [handler]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.session :refer [wrap-session]])
  (:gen-class))

(defn -main
  ""
  [& args]
  (run-jetty (-> handler
                 wrap-params
                 wrap-session) {:port 5000}))
