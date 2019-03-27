(ns marvel-cljs.routes
  (:import goog.history.Html5History)
  (:require-macros [secretary.core :refer [defroute]])
  (:require [goog.history.EventType :as EventType]
            [goog.events :as gevents]
            [marvel-cljs.character.views :as character.view]
            [secretary.core :as secretary]
            [marvel-cljs.characters.views :as characters.view]
            [re-frame.core :refer [dispatch]])
  )

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (gevents/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))


(defn app-routes
  []
  (secretary/set-config! :prefix "#")

  (defroute "/character" []
            (dispatch [:set-active-panel [character.view/character-panel :character]]))

  (defroute "/" []
            (dispatch [:set-active-panel [characters.view/main-panel :characters]]))
  (hook-browser-navigation!))