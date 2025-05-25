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

(let [num (atom 1) s1 @num]
  (swap! num inc)
  (println "State 1: " s1)
  (println "Current state: " @num))
(defn shuffle-speed
  [zombie]
  (* (:cuddle-hunger-level zombie)
     (- 100 (:percent-deteriorated zombie))))
(defn shuffle-alert
  [key watched old-state new-state]
  (let [sph (shuffle-speed new-state)]
    (if (> sph 5000)
      (do
        (println "Run, you fool!")
        (println "The zombie's sph is now " sph)
        (println "This message brought to your courtesy of " key))
      (do
        (println "All is well with" key)
        (println "Cuddle hunger: " (:cuddle-hunger-level new-state))
        (println "Percent deteriorated: " (:percent-deteriorated new-state))
        (println "Sph: " sph)))))
(defn percent-deteriorated-validator
  [{:keys [percent-deteriorated]}]
  (or (and (>= percent-deteriorated 0)
           (<= percent-deteriorated 100))
      (throw (IllegalStateException. "That's not matchy"))))
(def bobby
  (atom {:cuddle-hunger-level 0 :percent-deteriorated 0}
        :validator percent-deteriorated-validator))

(def sock-varieties
  #{"darned" "argyle" "wool" "horsehair" "mulleted"
    "passive-aggressive" "striped" "polka-dotted"
    "athletic" "business" "power" "invisible" "gollumed"})
(defn sock-count
  [sock-variety count]
  {:variety sock-variety
   :count count})
(defn generate-sock-gnome
  "Create an initial sock gnome state with no socks"
  [name]
  {:name name :socks #{}})
(defn steal-sock
  [gnome dryer]
  (dosync
   (when-let [pair (some #(if (= (:count %) 2) %) (:socks @dryer))]
     (let [updated-count (sock-count (:variety pair) 1)]
       (alter gnome update-in [:socks] conj updated-count)
       (alter dryer update-in [:socks] disj pair)
       (alter dryer update-in [:socks] conj updated-count)
       (println (:name @gnome) "stole a sock of variety" (:variety pair) "from" (:name @dryer))))))
(defn similar-socks
  "Check if two socks are similar based on variety"
  [target-sock sock-set]
  (filter #(= (:variety %) (:variety target-sock)) sock-set))
(def sock-gnome (ref (generate-sock-gnome "Gnorman")))
(def dryer (ref {:name "LG 1337" :socks (set (map #(sock-count % 2) sock-varieties))}))

(defn -main
  "main"
  [& args]
  (reset! fred {:cuddle-hunger-level 22
                :percent-deteriorated 2})
  (add-watch fred :fred-shuffle-speed shuffle-alert)
  (swap! fred update-in [:percent-deteriorated] + 1)
  ;;(swap! bobby update-in [:percent-deteriorated] + 200)
  (println (:socks @dryer))
  (steal-sock sock-gnome dryer)
  (println "Gnorman's socks:" (:socks @sock-gnome))
  (println (similar-socks (first (:socks @sock-gnome)) (:socks @dryer))))

