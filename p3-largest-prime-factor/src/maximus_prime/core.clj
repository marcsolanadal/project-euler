;;------------------------------------------------------------------------------------
;; PROBLEM
;;------------------------------------------------------------------------------------

;; The prime factors of 13195 are 5, 7, 13 and 29.
;; What is the largest prime factor of the number 600851475143 ?

(ns maximus-prime.core
  (:gen-class))

(use 'criterium.core)

;;------------------------------------------------------------------------------------
;; FIRST SOLUTION
;;------------------------------------------------------------------------------------

(defn factors
  [n]
  (conj (filter odd? (range 2 (inc (Math/sqrt n)))) 2))

(defn prime?
  [n]
  (if (empty? (filter #(zero? (mod n %)) (factors n)))
    true
    false))

(defn prime-list
  [n]
  (conj (filter #(prime? %) (range n)) 2))

(defn maximus-prime
  [n]
  (last (filter #(zero? (mod n %)) (prime-list (/ n 2)))))

;; 1.983361 µs
;;(with-progress-reporting (bench (prime? 100) :verbose))

;; 33.528382 ms
;;(with-progress-reporting (bench (maximus-prime 13195) :verbose))

;; 463.456524 ms
;;(with-progress-reporting (bench (maximus-prime 131950) :verbose))

;; 8.252301 sec
;;(with-progress-reporting (bench (maximus-prime 1319500) :verbose))



;;------------------------------------------------------------------------------------
;; SECOND SOLUTION
;;------------------------------------------------------------------------------------


(defn factors
  [n]
  (conj (range 3 (Math/sqrt n) 2) 2))

(defn factors1
  [n]
  (conj (filter odd? (range 2 (inc (Math/sqrt n)))) 2))

(defn factors2
  [n]
  (loop [current 3
         factors []]
         (if (< current (Math/sqrt n))
           (recur (+ current 2)(conj factors current))
           factors)))

;; 2.998127 µs
;;(with-progress-reporting (bench (factors 600851475143) :verbose))

;; 42.648946 ms
;;(with-progress-reporting (bench (factors2 600851475143) :verbose))

;; 1.735241 µs
;;(with-progress-reporting (bench (factors0 600851475143) :verbose))

(defn factors
  [n]
  (conj (range 3 (Math/sqrt n) 2) 2))

(defn prime?
  [n]
  (if (empty? (filter #(zero? (mod n %)) (factors n)))
    true
    false))

;; This is not a list of primes is a filter
(defn divisible-prime-list
  [n]
  (loop [current 3
         primes []]
    (if (< current (Math/sqrt n))
      (if (zero? (mod n current))
        (recur (+ current 2)(conj primes current))
        (recur (+ current 2) primes))
      primes)))

(defn maximus-prime
  [n]
  (last (filter prime? (divisible-prime-list n))))

;; 17.926413 µs
;;(with-progress-reporting (bench (maximus-prime 13195) :verbose))

;; 84.293876 µs
;;(with-progress-reporting (bench (maximus-prime 1319500) :verbose))

;; 21.229852 ms
(with-progress-reporting (bench (maximus-prime 600851475143) :verbose))

;;------------------------------------------------------------------------------------
;; INTERNET SOLUTION
;;------------------------------------------------------------------------------------

(defn get-max-prime-factor [num cur limit]
  (if (> cur limit)
    num
    (if (= num cur)
        num
        (if (zero? (mod num cur))
          (get-max-prime-factor (/ num cur) cur limit)
          (get-max-prime-factor num (inc cur) limit)))))

(defn max-prime-factor [num]
    (let [limit (long (Math/sqrt num))]
        (get-max-prime-factor num 2 limit)))

(count [1 2 3])


;; 673.752950 µs
(with-progress-reporting (bench (max-prime-factor 600851475143) :verbose))
