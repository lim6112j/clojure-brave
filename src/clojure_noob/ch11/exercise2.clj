(ns clojure-noob.ch11.exercise2
  (:require
   [clojure.core.async :as a]
   [clojure.string :as s]))
(defn hot-dog-machine
  []
  (let [in (a/chan)
        out (a/chan)]
    (a/go (a/<! in)
          (a/>! out "hot dog"))
    [in out]))
(defn hot-dog-machine-v2
  [hot-dog-count]
  (let [in (a/chan)
        out (a/chan)]
    (a/go (loop [hc hot-dog-count]
            (if (> hc 0)
              (let [input (a/<! in)]
                (if (= 3 input)
                  (do (a/>! out "hot dog")
                      (recur (dec hc)))
                  (do (a/>! out "wilted lettuce")
                      (recur hc))))
              (do (a/close! in) (a/close! out)))))
    [in out]))
(let [c1 (a/chan) c2 (a/chan) c3 (a/chan)]
  (a/go (a/>! c2 (s/upper-case (a/<! c1))))
  (a/go (a/>! c3 (s/reverse (a/<! c2))))
  (a/go (println (a/<! c3)))
  (a/>!! c1 "redrum"))

(defn -main
  [& args]
  ; (let [[in out] (hot-dog-machine)]
  ;   (a/>!! in "pocket lint")
  ;   (println (a/<!! out)))
  (let [[in out] (hot-dog-machine-v2 2)]
    (a/>!! in "pocket liint")
    (println (a/<!! out))
    (a/>!! in 3)
    (println (a/<!! out))

    (a/>!! in 3)
    (println (a/<!! out))
    (a/>!! in 3)
    (a/<!! out)))
