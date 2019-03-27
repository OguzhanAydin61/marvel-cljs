(ns marvel-cljs.character.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [marvel-cljs.characters.subs]
            [marvel-cljs.util :as util]
            ))



(defn character-detail-page
  []
  [(fn []
     (let [character (get (-> @(subscribe [:marvel-db]) :data :results) (:id @(subscribe [:display])))]
       [:div [:div
              {:class "navbar"}
              [:div {:class "dropdown"}
               [:button {:class "dropbtn"}
                [:i {:class "fa fa-caret-down"}]
                "Links"]
               [:div {:class "dropdown-content"}
                (for [links (:urls character)]
                  [:a {:href (:url links) :target "_blank"} (:type links)]
                  )
                ]
               ]
              ]
        [:div {:style {:flex-basis "40%" :margin 30 :text-align "center"}}
         [:h1
          [:div {:style {:flex-basis "40%" :margin 30 :text-align "center"}}
           [:img {:style {:border-radius "8px"} :src (util/get-image (-> character :thumbnail :path))}]
           [:p {:style {:color "white"}} (str "Character Name: " (:name character))]
           [:div {:style {:color "white"}} [(fn []
                                              (when-not (clojure.string/blank? (:description character))
                                                (str "Description: " (:description character))))]]
           ]
          ]
         ]]
       ))])


(defn character-panel
  []
  (r/create-class
    {
     :component-did-mount #()
     :reagent-render      (fn []
                            [character-detail-page]
                            )
     }))
