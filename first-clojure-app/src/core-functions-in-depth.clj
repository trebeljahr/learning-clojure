; the idea of sequences (seqs)
(defn greet [name] (str "Hello " name))

(map greet ["Ruben" "Alexa" "Rene" "Anna"])
(map greet '("Sophia" "Jane" "Rudi" "Albert"))
(map greet #{"Abby" "Kurt" "Jenny" "Jordi"})
(map #(greet (second %)) {:name "World"})
; strings are seqs?
(first "Hello")
(rest "Hello")
; the string join is necessary because map returns 
; the string as a list
(clojure.string/join
 "" (map
     (fn [letter] (str letter "-"))
     "Hello"))

; the idea of using first, rest on seqs
(first ["This" "is" "a" "text"])
(rest ["This" "is" "a" "text"])

(map str ["a" "b" "c"] ["A" "B" "C"])
(list (str "a" "A") (str "b" "B") (str "c" "C"))

(map str ["b" "c" "d"] ["e" "f"])
(list (str "b" "e") (str "c" "f"))

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])

(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

; this is essentially zipping of two lists. 
(map unify-diet-data human-consumption critter-consumption)


; some statistics fun haha


(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn sqr [x] (* x x))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(def statistics '(2 4 4 4 5 5 7 9))
(def deviations (map
                 (fn [e] (sqr
                          (- e (nth (stats statistics) 2))))
                 statistics))
(def variance (nth (stats deviations) 2))
(def populationStandardDeviation (Math/sqrt variance))
(str populationStandardDeviation)

(def persons [{:name "Rico" :age 21} {:name "Ale" :age 34}])
(map :name persons)
(map :age persons)

; let's mess with time! 
(def years {:first 1981 :second 1956 :third 2012})
(defn forwardOneYear [new-map [key val]]
  (assoc new-map key (inc val)))
(reduce forwardOneYear {} years)

(defn forwardAThousandYears [new-map [key val]]
  (assoc new-map key (+ 1000 val)))
(reduce forwardAThousandYears {} years)
; it's the future!!! 


; building map using reduce 
(defn myMap [func sq]
  (reduce (fn [agg val]
            (into agg [(func val)])) [] sq))
(myMap inc [1 2 3])

; building filter using reduce 
(defn myFilter [func sq]
  (reduce (fn [agg val]
            (if (func val)
              (into agg [val])
              (let [x agg] x))) [] sq))
(defn biggerThan3 [n] (> n 3))
(biggerThan3 5)
(myFilter biggerThan3 [1 2 3 4 5])


; building some using reduce 


(defn mySome [func sq]
  (reduce
   (fn [agg val]
     (if (true? agg)
       (let [out true] out)
       (if (func val)
         (let [agg true] agg)
         (let [agg nil] agg))))
   nil sq))
(mySome even? '(1 2 3 4))

; using take 
(take 2 [10 9 8 7])
; and drop
(drop 2 [10 9 8 7])

(def jobs [{:pay 1200 :job "Wordpress Site"}
           {:pay 2500 :job "Shoppify Integration"}
           {:pay 300 :job "Blog Maintenance"}
           {:pay 800 :job "Photoshoot"}
           {:pay 999 :job "Website Design"}
           {:pay 1500 :job "Ghost Gatsby Site"}])

(defn good [job] (> (:pay job) 1000))
(defn bad [job] (< (:pay job) 1000))

; the "good" jobs
(myFilter good jobs)
(filter good jobs)
(= (filter good jobs) (myFilter good jobs))

; the "bad" jobs
(myFilter bad jobs)
(filter bad jobs)
(= (filter bad jobs) (myFilter bad jobs))

