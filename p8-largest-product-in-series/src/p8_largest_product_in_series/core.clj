(ns p8-largest-product-in-series.core
  (:require [clojure.java.io :refer :all])
  (:gen-class))

(defn window-product
  "Does the product of the numbers inside the window starting from the index
  number to the index plus window number.
  If the parameters are in bounds it returns the value and the numbers of the
  product. If the parameters are out of bounds it returns nil.
  TODO: Implement negative index support."
  [number index window-size]
  (loop [i index
         product 1
         num-list '()]
    (if (> (+ index window-size) (count number))
      nil
      (if (>= i (+ index window-size))
        [product num-list]
        (recur (inc i)
               (* product (nth number i))
               (conj num-list (nth number i)))))))

(defn largest-product
  "Finds the largest product with a given window inside a large number."
  [number window]
  (loop [n 0
         max-product 0
         max-numbers '()]
    (if (= n (- (count number) (dec window)))
      (println [max-product (reverse max-numbers)])
      (let [[product num-list] (window-product number n window)]
        (if (> max-product product)
          (recur (inc n) max-product max-numbers)
          (recur (inc n) product num-list))))))

(defn num-string->int-list
  "Convert a string of numbers into a list of integers."
  [string]
  (map (fn [^Character c] (Character/digit c 10)) string))

(defn get-number
  "Opens the file in the specified path and it reads the diferent lines. Then it
  joins all the lines into one string. That string is converted into a list of
  integers. Finally, the largest-product function is called with the number list
  as a parameter."
  [path window]
  (with-open [rdr (reader path)]
    (let [number (num-string->int-list (apply str (line-seq rdr)))]
      (largest-product number window))))

(defn -main
  "Solution of the problem 8 from https://projecteuler.net"
  [& args]
  (println "Find the thirteen adjacent digits in the 1000-digit number that")
  (println "have the greatest product. What is the value of this product?\n")
  (get-number "./resources/1000-digit-number.txt" 13))
