(ns first-clojure-app.core
  (:gen-class))

;; the obligatory hello world °° 
(defn greeting [greet]
  (str greet " World! You know - I am a little teapot!"))
(greeting "Hello")

;; some simple values - by themselves they do nothing. 
;; one can not even access them anymore, when used this way
1
"a string"
["a" "vector" "of" "strings"]

;; this does something at least. 
(+ 1 2 3)

(str "A" "B" "C"
     (if true
       (do (str "D" " AND E!"))))

(when true (str "Success!" " Something Else!"))

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
;; now on to some "real" functions of our own doings.

;; this is how you define one
(defn my_function [argument]
  (str "This is your argument: " argument))
;; note that it says defn not def! Subtle but important difference 

;; this is how you call it
(my_function "hello")

;; you can have functions with multiple arguments
(defn my_other_function [argument argument2 argument3]
  (str argument " " argument2 " " argument3))
(my_other_function "Hello" "there" "buddy")

;; you can have variadic functions like this
(defn variadic_func [argument & others]    (str argument " " (clojure.string/join " " others)))
(variadic_func "Hi" "my" "friend" "you" "are" "cool")

;; higher order functions like this
(defn higher_order_func [fnArg] (fnArg " world!"))
(defn function_argument [arg] (str "Hello " arg))
(higher_order_func function_argument)

;; there are also some built in higher order functions like map for example
(inc 2)
(def my_numbers [1 2 3])
(map inc my_numbers)
(defn double [arg] (* arg 2))
(map double my_numbers)

;; functions can have docstrings - which is nice - like so: 
(defn my_docstring_func
  "This can be a short description of the function!"
  [arg] (* arg arg))
(my_docstring_func 4)

;; functions can do different things based on the numbers of inputs given
(defn awesome_func
  ([one_arg] (str one_arg))
  ([one_arg two_arg] (* one_arg two_arg))
  ([one_arg two_arg three_arg]
   (+ one_arg (get two_arg three_arg))))

(awesome_func "Hello")
(awesome_func 2 5)
(awesome_func 1 {:numberToAdd 2} :numberToAdd)

(defn overload_me
  ([arg second_arg] (str arg " " second_arg))
  ([arg] (str "Hello " arg)))

(overload_me "Hello" "world")
(overload_me "world")

;; Clojure has argument destructuring for functions. Much like javascript... 
;; wait that is the wrong order - javascript has destructuring much like clojure/LISP
(defn destructured
  [[first_thing & other_things]]
  (str first_thing " " (clojure.string/join " " other_things) "!!!!!!"))
(destructured ["Scream" "this" "out" "loud"])

(defn destructure_map
  [{key1 :key1 key2 :key2}]
  (str key1 " " key2))
(destructure_map {:key1 "Hello" :key2 "World!"})

(defn destructure_keys
  [{:keys [key1 key2 key3 key4]}]
  (str key1 " " key2 " " key3 " " key4 " cool!"))
(destructure_keys {:key1 "Hello" :key2 "World" :key3 "this" :key4 "is"})

;; There are anonymous functions. They do not have a name and 
(fn [arg] (str arg ""))
;; our own inc. 
(map (fn [arg] (+ arg 1)) [1 2 3])
(map inc [1 2 3])
;; another way to write these looks like this. it is super short. holy. 
#(+ % 1)
;; here is our inc. again ^^ this time even shorter and a little more cryptic
(map #(+ % 1) [1 2 3])

;; let's say you want to have more than one argument - well simple. 
;; %1 is the first, %2 the second etc.
(#(+ %1 %2 %3) 1 2 3)
;; %& will access the rest of the values
(#(map inc %&) 1 2 3)

;; Here is closures - also very neat. 
(defn give_me_a_closure [arg]
  (fn [second_arg] (* second_arg arg)))
(def multByTwo (give_me_a_closure 2))
(multByTwo 4)
(def multByThree (give_me_a_closure 3))
(multByThree 4)
(def multByFour (give_me_a_closure 4))
(multByFour 4)

(let [x 3] x)

(def names ["Alfredo" "Hans" "Günther"])
(let [selected-names (take 3 names)] selected-names)
;; let makes it's own scope - see how the def names does not conflict with the let names
(let [names (take 2 names)] names)
(let [[first-name & other-names] names] [first-name other-names])
(def character ["Rico" "male" "blue eyes"])
(let [[name & attributes] character] (recur attributes (into []) (set [name attribute])))

;; the into operator inserts vectors into another vector
(into [:a] (set [:b :b :c]))
(into names ["Albert" "Einstein"])

;; this is an example of a simple loop
(loop [i 0]
  (println (str "We are in iteration: " i))
  (if (> i 3)
    (println "Loop is over!")
    (recur (inc i))))

;; this is an example of a recursive function
(defn recursive-fn ([] (recursive-fn 0))
  ([number]
   (println number)
   (if (= number 3)
     (println "Function call is over!")
     (recursive-fn (+ number 1)))))

(recursive-fn)

;; there are also regular expressions - like this: 
(def my_regex #"text")
(re-find my_regex "This is a text!")

;; we can replace things within strings using regex like so:
(str (clojure.string/replace
      "hello this is my text"
      my_regex
      "replacement"))

;; there is also the Javascript style reduce
(reduce + [2 3 4 5 6])
(reduce str ["Hello " "world"])
;; we can write our own reducers as well. this one sums up the strings and numbers of a list of maps
(defn my_reducer [agg value] (let [out {:string (str (get agg :string) " " (get value :string))
                                        :number (+ (get agg :number) (get value :number))}] out))
(def to_be_reduced [{:string "Hello" :number 10} {:string "world" :number 5}])
(reduce my_reducer {:string "" :number 0} to_be_reduced)
;; when calling reduce the first arg is the function, the second the initial value and the third the list to be reduced
;; the function passed in has to return something and is fed the aggregated value and the current value of the list

;; some last comment - this is just a very simple cheat sheet showing all
;; of the cool things there are in clojure but it is by no means an 
;;extensive list and to be truly able to use this language you have
;; to apply all of the things above confidently, combining them 
;; into neat code that actually does cool stuff! So write something epic aaand...
;; have fun! 

