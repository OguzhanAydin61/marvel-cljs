(ns marvel-cljs.prod
  (:require
    [marvel-cljs.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

