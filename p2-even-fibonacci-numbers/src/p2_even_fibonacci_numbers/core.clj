(ns p2-even-fibonacci-numbers.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; Each new term in the Fibonacci sequence is generated by adding the previous
;; two terms.
;; By starting with 1 and 2, the first 10 terms will be:
;;       1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
;; By considering the terms in the Fibonacci sequence whose values do not exceed
;; four million, find the sum of the even-valued terms.

;; Our solution uses a loop/recur structure

(defn calculate-fibo-sum
  [num]
  (loop [x 0 y 1 sum 0]
    (let [next (+ x y)]
      (if (< y num)
        (if (even? next)
          (recur y next (+ next sum))
          (recur y next sum))
        sum))))

(with-out-str (time (calculate-fibo-sum 4000000)))

;; We're still using variables to store data. This is our problem. We need to
;; think functions.

;; Solution crating a lazy-sequence and then filtering the values
;; This solution has much better performance

(def fibo (lazy-cat [0 1]
                    (map + fibo (rest fibo))))

(with-out-str (time
              (reduce + (take-while (partial >= 4000000)
                                    (filter even? fibo)))))

