(ns accept-example.httpd
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :as httpkit]))

(defrecord HTTPServer [ring-handler]
  component/Lifecycle
  (start [component]
    (let [server (httpkit/run-server ring-handler {:port 8080})]
      (assoc component :httpd server)))
  (stop [component]
    (if-let [server (:httpd component)]
      (server)) ; calling server like this stops it, if started
    (assoc component :httpd nil)))

(defn new-server [ring-handler]
  (->HTTPServer ring-handler))
