(ns marvel-cljs.navigation.subs
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub
  :name
  (fn [db _]
    (:name db)))

(rf/reg-sub
  :api-result
  (fn [db _]
    (:api-result db)))

(rf/reg-sub
  :current-user
  (fn [db _]
    (:current-user db)))

(rf/reg-sub
  :active-panel
  (fn [db _]
    (:active-panel db)))
