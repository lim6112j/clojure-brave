(ns clojure-noob.ch9.exercise)
(future (Thread/sleep 4000)
        (println "I'll print after 4 seconds"))
(println "I'll print immediately")
(let [result (future (println "this prints once")
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))
(deref (future (Thread/sleep 1000) 0) 10 5)
(def jackson-5-delay
  (delay (let [message "Just call my name and I'll be there"]
           (println "First deref: " message)
           message)))
(force jackson-5-delay)
(def gimli-headshots ["serious.jpg" "fun.jpg" "playfun.jsp"])
(defn email-user
  [email-address]
  (println "Sending headshot nofification to" email-address))
(defn upload-document
  "Needs to be implemented"
  [headshot]
  true)
(let [notify (delay (email-user "and-my-axe@gmail.com"))]
  (doseq [headshot gimli-headshots]
    (future (upload-document headshot)
            (force notify))))
(def my-promise (promise))
(deliver my-promise (+ 1 2))
(println "my-promise : " @my-promise)
