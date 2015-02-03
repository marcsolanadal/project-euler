(ns p7-10001st-prime.core-test
  (:require [clojure.test :refer :all]
            [p7-10001st-prime.core :refer :all]))

(testing "Should identify primes"
  (is (= true (prime? 13 0)))
  (is (= false (prime? 10 0)))
  )

;; "find prime"
;; "find next prime"
