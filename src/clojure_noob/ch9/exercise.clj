(ns clojure-noob.ch9.exercise)
(future (Thread/sleep 4000)
        (println "I'll print after 4 seconds"))
(println "I'll print immediately")
(let [result (future (println "this prints once")
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))
(deref (future (Thread/sleep 1000) 0) 10 5)
