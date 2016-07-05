(ns accept-example.system
  (:require [com.stuartsierra.component :as component]
            [accept-example.httpd :as httpd]))

(defn init [app]
  (component/system-map
   :httpd (httpd/new-server app)))
