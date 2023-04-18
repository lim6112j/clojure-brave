(ns clojure-noob.ch8.exercise)
(defmacro infix
  "User thsi macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))
(defmacro infix-2
  [[operand1 op operand2]]
  (list op operand1 operand2))
(defmacro and
  {:added "1.0"}
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and# (and ~@next) and#))))
; (let [result expression]
;   (println result)
;   result)
(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))
(println (list '+ 1 (inc 1))) ; normal quote
(println `(+ 1 ~(inc 1))) ;syntax quote
