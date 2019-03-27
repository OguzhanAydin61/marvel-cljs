(ns marvel-cljs.characters.events
  (:require [marvel-cljs.util :as util]
            [day8.re-frame.http-fx]
            [marvel-cljs.db :as db]
            [re-frame.core :refer [reg-event-db reg-event-fx]]
            [marvel-cljs.effects]))


(reg-event-fx
  :get-marvel
  (fn [{:keys [db]} [_ data]]
    (let [uri "/characters"]
      {:http-xhrio (util/create-request-map :get uri
                                            :get-marvel-result-ok
                                            data
                                            :get-marvel-result-not-ok)})))


(reg-event-db
  :set-more-data
  (fn [db [_ more-data-hash]]
    (assoc db :param-get more-data-hash)))