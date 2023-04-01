(ns clojure-noob.ch5.exercise)
(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    (println "You got a raise")
    (println "Better luck next year")
    )
  )

(defn analysis
  [text]
  (str "Character count: " (count text)))
(defn analyze-file
  [filename]
  (analysis (slurp filename)))
(defn sum
  ([vals] (sum vals 0))
  ([vals acc] 
   (if (empty? vals)
     acc
     (sum (rest vals) (+ acc (first vals))))))
