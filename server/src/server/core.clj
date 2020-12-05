(ns server.core
  (:use ring.adapter.jetty)
  (:require [compojure.core :refer [defroutes context GET]]
            [compojure.route :as route]
            [server.handler :refer [handler]])
  (:gen-class))

(defn -main
  ""
  [& args]
  (run-jetty handler {:port 5000}))
