(ns marvel-cljs.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :refer [dispatch-sync dispatch subscribe]]
    [marvel-cljs.navigation.events]
    [marvel-cljs.routes :refer [app-routes]]
    [marvel-cljs.navigation.views :as views]))


(defn mount-root []
  (reagent/render [views/main-panel] (js/document.getElementById "app"))
  )

(defn ^:export run
  []
  (app-routes)
  (dispatch-sync [:initialize])
  (mount-root)
  )
