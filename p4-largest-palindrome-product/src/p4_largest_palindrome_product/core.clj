(ns p4-largest-palindrome-product.core
  (:gen-class))

(use 'criterium.core)

;;(with-progress-reporting (bench (prime? 100) :verbose))

(defn palindrome?
  [n]
  (= (reverse (str n))(seq (str n))))

(with-progress-reporting
  (bench
    (apply max
      (filter palindrome?
        (for [x (range 500 1000)
              y (range 500 1000)]
          (* x y)))) :verbose))


;;(defn palindrome?
;;  [num index counter]
;;  (if (not= (nth num (- index 1)(nth num (- counter index)))
;;    false
;;    (if (= index (/ counter 2))
;;      true
;;      (palindrome? num (dec index) counter)))))

;;(palindrome? number (count number) (count number))
