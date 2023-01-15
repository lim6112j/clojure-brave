(ns clojure-noob.ch3.exercise)
(defn hello []
  (println "hello"))
(def aList '(1 2 3))
(def aVector [1 2 3])
(def aHashMap (hash-map :a 1 :b 2))
(def aHashSet (hash-set :a :b :a))
(defn adds100
  [x]
  (+ 100 x))
(defn dec-maker
  [x]
  #(- % x))
(defn mapset
  [f x]
  (set (map f x)))
