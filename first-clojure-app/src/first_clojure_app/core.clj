(ns first-clojure-app.core
  (:gen-class))

(defn greeting [greet]
  (str greet " World! You know - I am a little teapot!"))

(greeting "Hello")

1
"a string"
["a" "vector" "of" "strings"]

(+ 1 2 3)

(str "A" "B" "C"
     (if true
       (do (str "D" " AND E!")))) j

(when true (str "Success!"
                " Something Else!"))

(if false
  "Hello there"
  "Well...")

(str "Hello" " World")
