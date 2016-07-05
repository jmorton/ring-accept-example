(ns accept-example.calculator
  (:require [compojure.core :refer :all]
            [accept-example.respond :refer [defaccept]]
            [clojure.core.match :as m]
            [clojure.data.json :as j]))

;; Basic - albeit trivial - functions defining resources...

(defn add
  [{x "x" y "y"}]
  (apply +
         [(Integer/parseInt x)
          (Integer/parseInt y)]))

(defn multiply
  [{x "x" y "y"}]
  (apply * x y))

;; Responses

(defn to-json
  [response]
  (update-in response [:body] j/write-str))

(defn to-xml
  [response]
  (assoc-in response [:body] "<xml>rocks!</xml>"))

(defn to-text
  [response]
  (update-in response [:body] prn-str))

(defaccept respond-to
  "application/json" to-json
  "application/xml" to-xml
  "text/plain" to-text)

;; Routes

(defroutes calculator-routes
  (GET "/add" {params :params :as request}
       (respond-to request {:body {:answer (add params)}}))
  (GET "/multiply" {params :params :as request}
       (respond-to request {:body {:answer (multiply params)}})))

;; Examples

(comment
  (calculator-responses
   {:accept ["application/json"]}
   {:body {:answer 42}})
  (calculator-responses
   {:accept ["application/xml"]}
   {:body {:answer 42}}))
