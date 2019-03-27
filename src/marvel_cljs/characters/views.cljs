(ns marvel-cljs.characters.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [reagent.core :as r]
    [marvel-cljs.util :as util]
    [marvel-cljs.characters.subs]
    [goog.dom :as dom]))


(defn- get-scroll []
  (-> (dom/getDocumentScroll) (.-y)))


(defn show-count
  []
  [:div
   (-> @(subscribe [:count]))
   ]
  )


(defn load-more-button
  []
  [:div :align "center" [:button {:style
                                            {:color "blue" :background-color "red"}
                                  :on-click (fn
                                              []
                                              (dispatch [:set-more-data (update @(subscribe [:param-get]) :limit * 2)])

                                              (dispatch [:get-marvel @(subscribe [:param-get])])
                                              )
                                  }
                         "Load More Data"]])


(defn characters-show
  []
  [:div {:style {:overflow "scroll" :height "auto" :width "auto" :display "flex" :flex-wrap "wrap" :justify-content "center" :text-align "center"}}
   (for [[index item] (keep-indexed #(vector %1 %2) (-> @(subscribe [:marvel-db]) :data :results))]
     [:div {:style {:flex-basis "30%" :margin 20}}
      [:a {:href "#/character"} [:div {:class "flip-card"} [:div {:class "flip-card-inner"} [:div {:class "flip-card-front"} [:img {:alt      "Avatar"
                                                                                                                                    :style    {:border-radius "8px"}
                                                                                                                                    :key      index
                                                                                                                                    :src      (util/get-image (-> item :thumbnail :path))
                                                                                                                                    :on-click #(dispatch [:set-display {:visible false :id index}])
                                                                                                                                    }
                                                                                                                              ]]
                                                            [:div {:class "flip-card-back"}
                                                             [:h1 {:style {:font-size "2vw" :max-width "150px"}} (:name item)]]
                                                            ]]]

      ]

     )
   ])


(defn name-input
  []
  [:div.text.input
   "Name: "
   [:input {:type  "text"
            :value @(subscribe [:name])
            :on-change
                   #((dispatch [:name-changed (-> % .-target .-value)]))
            }]])

(defn header-message
  []
  [:div
   {:align "center" :style {:color "red" :font "35px arial"}} "Marvel Characters"])

(defn navigation-panel
  []
  [:div
   [header-message]
   [characters-show]
   [load-more-button]
   ])

(defn main-panel
  []
  (r/create-class
    {
     :component-did-mount #(dispatch [:get-marvel @(subscribe [:param-get])])
     :reagent-render      (fn []
                            [navigation-panel]
                            )
     }))