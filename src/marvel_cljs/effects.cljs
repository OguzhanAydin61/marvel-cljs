(ns marvel-cljs.effects
  (:require [re-frame.core :refer [reg-fx]]
            [marvel-cljs.util :as util]))


(reg-fx
  :set-current-user!
  (fn [current-user]
    (util/set-item! "currentUser" current-user)))