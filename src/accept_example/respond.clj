(ns accept-example.respond)

;; 1. Intersection of keys works but isn't correct, because request
;;    accept headers support wild cards (e.g. text/*).
;; 2. Content-type checking should be done before executing result-value
;;    producing functions?

(defn best-match
  "Choose best renders given accept header of request"
  [request renders]
  (let [accept (request :accept)
        accept-media (into (sorted-set) (map :media-type accept))
        render-media (into (sorted-set) (keys renders))
        best (first (clojure.set/intersection accept-media render-media))]
    best))

(defn fallback
  "Default response when no acceptable content can be produced"
  [response]
  (merge response {:status 406}))

(defmacro defaccept [name & renders]
  `(defn ~name [request# response#]
     (let [renders# (hash-map ~@renders)
           best-media# (best-match request# renders#)
           best-render-fn# (renders# best-media# ~fallback)]
       (->> (assoc response# :content-type best-media#)
            (best-render-fn#)))))
