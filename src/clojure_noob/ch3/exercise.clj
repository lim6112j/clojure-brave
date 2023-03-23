(ns clojure-noob.ch3.exercise)
(defn hello []
  (println "hello"))
; map
{} ; empty map
{:first-name "ben" :last-name "lim"}
{"string-key" +} ; string-key to + function
; using hash-map function to make a map
(def aHashMap (hash-map :a 1 :b 2))
(def aHashSet (hash-set :a :b :a))
(get aHashMap :a) ; get :a value from aHashMap
(get {:a 0 :b 1} :c "unicorns?")  ; default value
(get-in {:a 0 :b {:c "ho hum"}} [:b :c]) ; lookup in nested maps
({:a 1 :b 2} :a) ; map is function-like
; keywords
(:a {:a 1 :b 2}) ; == (get {:a 1 :b 2} :a),  keywords like function
; vectors
[1 2 3]
(def aVector [1 2 3])
(get [1 2 3] 0) ; 0 idx value is 1
(vector 1 2 3) ; vector function creates vector
(conj [1 2 3] 4)
; => [1 2 3 4] 

; lists
'(1 3 4) 
; => (1 2 3) 
(def aList '(1 2 3))


(defn adds100
  "adds 100 function"
  [x]
  (+ 100 x))
(defn dec-maker
  "decrementer maker"
  [x]
  #(- % x))
(defn mapset
  [f x]
  (set (map f x)))
