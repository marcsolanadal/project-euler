(defproject p1-multiples-of-3-and-5 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [criterium "0.4.3"]]
  :main ^:skip-aot p1-multiples-of-3-and-5.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
