(ns p8-largest-product-in-series.core-test
  (:require [clojure.test :refer :all]
            [p8-largest-product-in-series.core :refer :all]))

(deftest window-product-test
  (testing "Should return a vector with the product and the list of numbers."
    (is (= '[2 (2 1)]
           (window-product (range 1 10) 0 2))))
  (testing "Should return nil in case of (index+window) is out of bounds."
    (is (= nil
           (window-product (range 1 10) 7 5)))))
