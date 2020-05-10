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

;; Conditionals 
(if true
  "Hello there"
  "Well...")

(if false
  "Hello there"
  "Well...")

;; All Nil Things
(nil? 1)
(nil? (str "Hello there"))
(nil? 0)
(nil? -1)
(nil? false)
(nil? nil)

(if nil
  "Nil is true value"
  "Nil is a lie!")

;; Logical operators!


(= 1 -1)
(= 1 1)
(= nil 2)
(= nil nil)

;; or returns the first true value
(or false false false false false "Hello there")
(or false true)

;; and returns the first false value or the last true value
(and false false)
(and false true)
(and true true "Hello!")

;; defining variables with def 

;; There are strings...
(def my_name "Rico")
(str "Hello " my_name)

;; and numbers...
(def my_age 21)
(str "I am " my_age " years old!")

;; and vectors...
(def my_greetings ["Hi" "Hello" "Howdy" "How are you"])
(get my_greetings 0)
(get my_greetings 1)

;; vectors can contain mixed types 
(def my_vectors ["HI" 0 true false -1 nil {:key "value"}])
(my_vectors 0)
(my_vectors 6)
(get my_vectors 2)

(conj my_vectors "This will be added to the end!")
(conj my_vectors [1 2])

;; and lists...
(def my_list '("Hi" "There" "Buddy"))
(nth my_list 0)
(nth my_list 2)

(def my_other_list (list "some" "other" "elements" "in" "a" "list"))
(conj my_other_list "added to the beginning!")

;; and sets...
(def my_set #{"my name" 20 false :some_key})
(conj my_set "my name")
(conj my_set "my different name!")
(def my_hash_set (hash-set 1 1 2 2))
(def my_vectors_as_a_set (set my_vectors))
(contains? my_set :some_key)
(contains? my_set "my name")
(contains? my_set "some other name?")
(:some_key my_set)
(get my_set "my name")

;; and map-literals...
(def my_person {:name "Rico" :age 21 :gender "male" :level "over 900"})
(str "My level is " (get my_person :level))
(str "Hello my name is " (get my_person :name) "."
     " I am " (get my_person :age) " years old and "
     "my level is " (get my_person :level))

;; and hash maps... 
(def my_level (hash-map :a " is over " :b 9000))
(str "My level" (get my_level :a) (get my_level :b))

(get my_level :c "this does not have value c")
(def my_character {:character my_person :greetings my_greetings})
(get-in my_character [:character :name])
(my_person :name)
(:name my_person)
(:non_existent_key my_person "This is the default value")

;; LET's do some functions shall we?! 
(+ 1 1 1 (- 1 3))
(last my_greetings)
(get my_person :name)
(or 2 1)
;; wait?! we have been doing functions all along? cool. 
;; now on to some real functions

;; this is how you define one
(defn my_function [argument]
  (str "This is your argument: " argument))
;; note that it says defn not def! Subtle but important difference 

;; this is how you call it
(my_function "hello")

(defn my_other_function [argument argument2 argument3]
  (str argument " " argument2 " " argument3))
(my_other_function "Hello" "there" "buddy")

(defn variadic_func [argument & others]    (str argument " " (clojure.string/join " " others)))
(variadic_func "Hi" "my" "friend" "you" "are" "cool")