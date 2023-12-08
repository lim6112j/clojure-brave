(ns clojure-noob.ch11.exercise
  (:require [clojure.core.async :as a]))
(defn upload
  [headshot c]
  (a/go (Thread/sleep (rand 100))
        (a/>! c headshot)))
(let [c1 (a/chan)]
  (upload "serious.jpg" c1)
  (let [[headshot channel] (a/alts!! [c1 (a/timeout 10)])]
    (if headshot
      (println "Sending headshot notification for" headshot)
      (println "Timed out!"))))
(let [c1 (a/chan)
      c2 (a/chan)]
  (a/go (a/<! c2))
  (let [[value channel] (a/alts!! [c1 [c2 "put!"]])]
    (println value)
    (= channel c2)))
(def hi-chan (a/chan))
(doseq [n (range 100)]
  (a/go (a/>! hi-chan (str "hi" n)))
  (println (a/<!! hi-chan)))
(let [t (a/thread "chili")]
  (a/<!! t))
(defn -main
  "main"
  [& args])
