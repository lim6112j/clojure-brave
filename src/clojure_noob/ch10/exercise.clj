(ns clojure-noob.ch10.exercise)
(def fred (atom {:cuddle-hunger-level 0
                 :percent-deteriorated 0}))
(let [zombie-state @fred]
  (if (>= (:percent-deteriorated zombie-state) 50)
    (future (println (:percent-deteriorated zombie-state)))))
(swap! fred
       (fn [current-state]
         (merge-with + current-state {:cuddle-hunger-level 1 :percent-deteriorated 1})))
(defn increase-cuddle-hunger-level
  [zombie-state increasy-by]
  (merge-with + zombie-state {:cuddle-hunger-level increasy-by}))

; this doesn't update fred state beacause it's not using swap! or reset! to update the atom
(increase-cuddle-hunger-level @fred 1)
; this updates fred state
(swap! fred increase-cuddle-hunger-level 1)
; built in update function instead of increase-cuddle-hunger-level
(update-in {:a {:b 2}} [:a :b] inc)
; => {:a {:b 3}}
(update-in {:a {:b 3}} [:a :b] + 10)
; => {:a {:b 13}}

(swap! fred update-in [:cuddle-hunger-level] inc)
