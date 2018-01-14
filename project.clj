(defproject hieroglyphs "0.1.0-SNAPSHOT"
  :description "A web-app converting Manuel de Codage transliteration of hieroglyphs to base64 png"
  :url "http://example.com/FIXME"
  :license {:name "GPL 3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.3"]
                 [environ "1.1.0"]]
  ;; :main hieroglyphs.core
  :min-lein-version "2.0.0"
  :heroku {:app-name "pure-sierra-27254"}
  :plugins [[environ/environ.lein "0.3.1"]
            [lein-heroku "0.5.3"]]
  :hooks [environ.leiningen.hooks]
  :java-source-paths ["src/java" "test/java"]
  :resource-paths ["resources/jsesh-6.5.5.jar" "resources/jseshGlyphs-6.5.5.jar" "resources/java-cup-11b-runtime.jar" "resources/java-cup-11b.jar" "resources/qenherkhopeshefUtils-6.5.5.jar"]
  :uberjar-name "hieroglyphs-standalone.jar"
  :profiles {:production {:env {:production true}}})
