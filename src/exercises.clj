; 1. Use the str, vector, list, hash-map, and hash-set functions.
(str "Wow... that was hard")
(clojure.string/join " " ["ahem..." "real hard."])
(clojure.string/join " " '("a" "lisp" "is" "a" "'list'" "processor"))
(def a_hash_map #{:name "Rico" :lastname "Trebeljahr"})
(def a_hash_set (hash-set "no" "more" "brownies"))
(let [a a_hash_set] a)
; 2. Write a function that takes a number and adds 100 to it.
(defn add100 [number] (+ number 100))
(add100 100)
; 3. Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction.
(defn dec-maker [numberToSubtract] (fn [number] (- number numberToSubtract)))
(def dec9 (dec-maker 9))
(dec9 10)

; 4. Write a function, mapset, that works like map except the return value is a set:
(defn map-set [thingToMap func] (set (map func thingToMap)))
(map-set [1 2 3 4 1] (fn [elem] (+ elem 1)))
; 5. Create a function that’s similar to symmetrize-body-parts except that it has to work 
; with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.
; 6. Create a function that generalizes symmetrize-body-parts and the function you created in Exercise 5.
(def alien-body [{:name "left-eye" :size 5}
                 {:name "left-leg" :size 5}
                 {:name "left-antennae" :size 5}
                 {:name "torso" :size 5}])
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn multiply-part [part howMany]
  (loop [parts []]
    (if (>= (count parts) howMany)
      parts
      (recur (into parts [{:name (str (:name part) (count parts))
                           :size (:size part)}])))))
(defn alienify [part]
  (let [otherPart (matching-part part)]
    (if (some? (re-find #"arm|foot|leg|eye" (:name part)))
      (let [out (into (multiply-part otherPart 2) (multiply-part part 2))] out)
      (let [out [part]] out))))

(defn complete-alien
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (alienify part)))
          []
          asym-body-parts))
(complete-alien alien-body)
