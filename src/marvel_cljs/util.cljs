(ns marvel-cljs.util
  (:require [ajax.core :as ajax]))

(defonce api-url "http://gateway.marvel.com/v1/public")

(defn set-item!
  [key val]
  (.setItem (.-localStorage js/window) key (.stringify js/JSON (clj->js val))))

(defn get-image
  [image-path]
  (str image-path "/portrait_xlarge.jpg"))


(defn create-request-map
  ([type uri on-success limit-param]
   (create-request-map type uri on-success limit-param nil))
  ([type uri on-success limit-param on-fail]
   (cond-> {
            :method          type
            :uri             (str api-url uri)
            :params          {:ts     1
                              :apikey "**"
                              :hash   "**"
                              :limit  (:limit limit-param)
                              :offset (:offset limit-param)}
            :format          (ajax/json-request-format)
            :response-format (ajax/json-response-format {:keywords? true})
            :on-success      (if (vector? on-success) on-success [on-success])
            :on-failure      (if (vector? on-fail) on-fail [on-fail])}
           (nil? on-fail) (dissoc :on-failure))))
