(ns marvel-cljs.db
  (:require [re-frame.core :refer [reg-cofx reg-fx]]))

(def default-db {
                 :name      "Oğuzhan"
                 :display   {:visible true :id 0}
                 :param-get {:limit 30 :offset 1}
                 :count     1})


(defn- set-current-user-map
  "Puts todos into localStorage"
  [todos]
  (.setItem js/localStorage "currentUser" (str todos)))


(reg-fx
  :current-user
  (fn [current-user]
    (set-current-user-map current-user)))


(defn- get-current-user-map
  []
  (into (sorted-map)
        (as-> (.getItem js/localStorage "currentUser") data
              (.parse js/JSON data)
              (js->clj data :keywordize-keys true))))

(reg-cofx
  :current-user
  (fn [cofx _]
    (assoc cofx :current-user (get-current-user-map))))