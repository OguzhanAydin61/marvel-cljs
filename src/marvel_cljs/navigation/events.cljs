(ns marvel-cljs.navigation.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx inject-cofx]]
    [re-frame.core :as rf]
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [marvel-cljs.character.events]
    [marvel-cljs.characters.events]
    [marvel-cljs.effects]
    [marvel-cljs.db :as db]
    [marvel-cljs.navigation.subs]
    ))

(reg-event-fx
  :get-marvel-result-not-ok
  (fn [{:keys [db]} [_ data]]

    ))

(reg-event-fx
  :get-marvel-result-ok
  (fn [{:keys [db]} [_ data]]
    {:db (assoc db :marvel-db data)}))


(reg-event-fx
  :initialize
  [(inject-cofx :current-user)]
  (fn [{:keys [current-user]} _]
    {:db (assoc db/default-db :current-user current-user)}))


(reg-event-fx
  :current-user
  (fn [{:keys [db]} [_ current-user]]
    {:db                (assoc db :current-user current-user)
     :set-current-user! current-user}))





(reg-event-db
  :set-active-panel
  (fn [db [_ active-panel]]
    (assoc db :active-panel active-panel)))


(reg-event-db
  :name-changed
  (fn [db [_ new-name-change]]
    (assoc db :name new-name-change)))


(reg-event-db
  :count-change
  (fn [db [_ change-count]]
    (assoc db :count change-count)))