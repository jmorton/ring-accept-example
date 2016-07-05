(ns user
  (:require [accept-example.system :as system]
            [accept-example.core :as core]
            [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [org.httpkit.server :as httpkit]))

(def sys nil)

(defn init
  []
  (alter-var-root #'sys (constantly (system/init #'core/app))))

(defn start
  []
  (alter-var-root #'sys component/start))

(defn stop
  []
  (alter-var-root #'sys (fn [s] (when s (component/stop s)))))

(defn go
  []
  (init)
  (start))

(defn reload []
  (stop)
  (refresh :after 'user/go))


