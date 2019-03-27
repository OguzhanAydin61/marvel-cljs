(ns marvel-cljs.characters.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :marvel-db
  (fn [db _]
    (:marvel-db db)))

(rf/reg-sub
  :display
  (fn [db _]
    (:display db)))

(rf/reg-sub
  :param-get
  (fn [db _]
    (:param-get db)))

(rf/reg-sub
  :count
  (fn [db _]
    (:count db)))