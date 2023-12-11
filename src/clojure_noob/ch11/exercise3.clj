(ns clojure-noob.ch11.exercise3
  (:require [clojure.core.async :as a]
            [clojure.string]))

(defn upper-case
  [in]
  (let [out (a/chan)]
    (a/go (while true (a/>! out (clojure.string/upper-case (a/<! in)))))
    out))
(defn reverser
  [in]
  (let [out (a/chan)]
    (a/go (while true (a/>! out (clojure.string/reverse (a/<! in)))))
    out))
(defn printer
  [in]
  (a/go (while true (println (a/<! in)))))
(def in-chan (a/chan))
(def upper-case-out (upper-case in-chan))
(def reverse-out (reverser upper-case-out))
(printer reverse-out)

(defn -main
  [& args]
  (a/>!! in-chan "redrum")
  (a/>!! in-chan "repaid"))

