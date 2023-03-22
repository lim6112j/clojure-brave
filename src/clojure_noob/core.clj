(ns clojure-noob.core
  (:gen-class))
(use 'clojure-noob.ch3.exercise)

(def dec9 (dec-maker 9))
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (hello)
  (println "a List : " aList)
  (println "a Vector : " aVector)
  (println "a hash-map" aHashMap)
  (println "a hash-set" aHashSet)
  (println "adds 100 3" (adds100 3))
  (println "dec 9 from 10" (dec9 10))
  (println "mapset inc [1, 1, 2,2]" (mapset inc [1 1 2 2]))
  (println "titleize : " (map titleize '("Empathy", "Decoration") ))
  )
