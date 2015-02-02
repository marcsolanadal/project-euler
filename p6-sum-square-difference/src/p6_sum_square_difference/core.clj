(ns p6-sum-square-difference.core
  (:gen-class))

(use 'criterium.core)

;; -----------------------------------------------------------------------------
;; My solution
;; -----------------------------------------------------------------------------

(defn sum-of-pot [num] (reduce + (map * num num)))
(defn pot-of-sum [num] (let [sum (reduce + num)] (* sum sum)))

;; 90.480764 µs
;;(with-progress-reporting
;;  (bench
;;    (let [num (range 1 101)] (- (pot-of-sum num) (sum-of-pot num)))
;;    :verbose))

;; -----------------------------------------------------------------------------
;; Internet solution (optimized with maths)
;; -----------------------------------------------------------------------------

;; sum(n) = n(n+1)/2
(defn sum-to [n] (/ (* n (+ n 1)) 2))

;; pot-of-sum(n) = (n(2n+1)(n+1))/6
(defn pot-of-sum [n] (/ (* n (+ (* 2 n) 1) (+ n 1)) 6))

(defn sum-square-diff
  [n]
  (let
    [sum (sum-to n)
     potsum (pot-of-sum n)]
    (- (* sum sum) potsum)))

;; 990.261771 ns
;;(with-progress-reporting
;;  (bench
;;    (sum-square-diff 100)
;;    :verbose))

;; -----------------------------------------------------------------------------
;; PAREDIT
;; -----------------------------------------------------------------------------

;; Keymaps
;; -----------------------------------------------------------------------------
;; ,W/,w (wrap)
;; ,S (splice)
;; ,O (split)
;; ,J (join)
;; ,I (raise subforms)
;; ,< (move parents left)
;; ,> (move parents right)

;; Commands
;; -----------------------------------------------------------------------------
;; g:paredit_mode=0
;; g:paredit_electric_return=0
;; g:paredit_leader = ','


() (1 2 (3 4) 5 6)
