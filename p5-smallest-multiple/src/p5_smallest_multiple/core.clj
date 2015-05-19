(ns p5-smallest-multiple.core
  (:gen-class))

;; 2520 is the smallest number that can be divided by each of the numbers from 1
;; to 10 without any remainder.

;; What is the smallest positive number that is evenly divisible by all of the
;; numbers from 1 to 20?

(use 'criterium.core)

;; My solution

(defn multiple?
  [num n limit]
  (if (= n limit)
    true
    (if (zero? (rem num n))
      (multiple? num (inc n) limit)
      false)))

(defn find-smallest-multiple
  [limit]
  (loop [n 2]
    (if (multiple? n 1 limit)
      n
      (recur (+ n 2)))))

;; (find-smallest-multiple 10)
;; Without optimizations: 1.640246 ms
;; Odd number optimization: 1.154761 ms
;; with == 0 insted of zero?: 1.461636 ms
;; with rem instead of mod: 933.095031 µs

;; (find-smallest-multiple 20)
;; Odd number optimization: 1.687146 min

;; Internet solution (Least Common Multiple)

(defn gcd [a b] (if (zero? b) a (recur b (mod a b))))  ; greatest common divisor
(defn lcm [a b] (/ (* a b) (gcd a b)))
(reduce #(lcm %1 %2) (range 1 21))

;; K = 20 --> 14.753294 µs
;; K = 10 --> 7.746159 µs
