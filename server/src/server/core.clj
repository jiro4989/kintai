(ns server.core
  (:use ring.adapter.jetty)
  (:gen-class))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello World"})

(defn -main
  ""
  [& args]
  (run-jetty handler {:port 5000}))
