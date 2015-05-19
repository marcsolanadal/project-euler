(ns p7-10001st-prime.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
;; we can see that the 6th prime is 13.
;; What is the 10 001st prime number?

(defn prime?
  [n x]
  (if (= n x)
    true
    (if (zero? (mod n x))
      (prime? n (inc x))
      false)))
