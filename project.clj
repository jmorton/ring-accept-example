(defproject accept-example "0.1.0-SNAPSHOT"
  :description ""
  :url "http://github.com/jmorton/accept-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/data.json "0.2.6"]
                 [com.stuartsierra/component "0.3.1"]
                 [compojure "1.5.1"]
                 [http-kit "2.1.18"]
                 [ring "1.5.0"]
                 [ring/ring-accept "0.1.0-snapshot"]]
  :profiles {:dev {:source-paths ["dev" "src"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/java.classpath "0.2.3"]]
                   :repl-options {:init-ns user}}})
