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

// Conditionals 
(if true 
  "Hello there"
  "Well...")

(if false
  "Hello there"
  "Well...")

// All Nil Things
(nil? 1)
(nil? (str "Hello there"))
(nil? 0)
(nil? -1)
(nil? false)
(nil? nil)

(if nil 
  "Nil is true value"
  "Nil is a lie!"
  )

// Logical operators!
(= 1 -1)
(= 1 1)
(= nil 2)
(= nil nil)

// or returns the first true value
(or false false false false false "Hello there")
(or false true)

// and returns the first false value or the last true value
(and false false)
(and false true)
(and true true "Hello!")

// defining variables with def 

// There are strings...
(def my_name "Rico")
(str "Hello " my_name)

// and numbers...
(def my_age 21)
(str "I am " my_age " years old!")

// and vectors...
(def my_greetings ["Hi" "Hello" "Howdy" "How are you"])
(get my_greetings 0)
(get my_greetings 1)

// and map-literals...
(def my_person {:name "Rico" :age 21 :gender "male" :level "over 900" })
(str "My level is " (get my_person :level))
(str "Hello my name is " (get my_person :name) "." 
  " I am " (get my_person :age) " years old and " 
  "my level is " (get my_person :level))

// and hash maps... 
(def my_level (hash-map :a " is over " :b 9000))
(str "My level" (get my_level :a) (get my_level :b))

(get my_level :c "this does not have value c")
(def my_character {:character my_person :greetings my_greetings})
(get-in my_character [:character :name])
(my_person :name) 
(:name my_person)
(:non_existent_key my_person "This is the default value")


