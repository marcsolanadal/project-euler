(ns p1-multiples-of-3-and-5.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(use 'criterium.core)

;; If we list all the natural numbers below 10 that are multiples of 3 or 5,
;; we get 3, 5, 6 and 9.The sum of these multiples is 23.

;; Find the sum of all the multiples of 3 or 5 below 1000.

(defn find-sum-multiples
  "It calculates the sum of the multiples of the vector specified."
   [target]
   (loop [count 0
          sum 0]
     (if (< count target)
       (if (or (= 0 (mod count 3))
                (= 0 (mod count 5)))
         (recur (inc count)(+ sum count))
         (recur (inc count) sum))
         sum)))

(with-out-str (time (find-sum-multiples 1000)))

;; CHALLANGE COMPLETE!!!

;; Questions
;; - How can I get rid of the let?
;; - Can we skip all the let?
;; - Is there a better way of doing this?

;; This solution is using filters. As you can see a filter is a really expensive
;; way to do this operation
(with-out-str (time
(reduce + (filter #(or (zero? (mod % 3))
                       (zero? (mod % 5)))
                  (range 1000)))))


;; This solution uses exact gneeration of numbers
(with-out-str (time
               (reduce + (set (concat (range 0 1000 3)(range 0 1000 5))))))

;; As we can see about the timings our solution was not so bad

;; TIMINGS
;; - Exact num generation   0.3ms
;; - My solution            1.2ms
;; - Filters                20ms
