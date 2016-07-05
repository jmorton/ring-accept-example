(ns accept-example.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.params :as ring-params]
            [ring.middleware.accept :as ring-accept]
            [accept-example.calculator :as calculator]))

(defroutes app-routes
  calculator/calculator-routes)

(def app (-> app-routes
             ring-params/wrap-params
             ring-accept/wrap-accept))
