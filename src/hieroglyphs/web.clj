(ns hieroglyphs.web
  (:require [ring.adapter.jetty :as jetty]
            [clojure.pprint :as pp]
            [ring.middleware.params :as pars]
            [ring.middleware.cors :refer [wrap-cors]]
            [environ.core :refer [env]])
  (:gen-class))

(:require 'JTestTransp)

(defn process-request
  "The main and only handler for this app"
  [request]
  (if (= "/favicon.ico" (:uri request))
    {:status 404
     :body ""
     :headers {}}
    (if-not (contains? (:params request)
                       "mdc")
      {:status 500
       :body "No transliteration provided"
       :headers { "Content-Type" "text/plain" }}
      (let [cadratHeight (Integer. (get (:params request) "height" "90"))]
        (let [smallSignsCentered (Boolean. (get (:params request) "centered" "false"))]
          {:status 200
           :body (JTestTransp/pipeline ((:params request) "mdc")
                                       cadratHeight
                                       smallSignsCentered)
           :headers { "Content-Type" "text/plain" }}))
      )
    )
  )

(defn -main
  "A web app for hieroglyph visualisation"
  [& [port-number]]
  (let [port-number (Integer. (or port-number (env :port) 5000))]
    (jetty/run-jetty (wrap-cors (pars/wrap-params process-request) #".*")
                     {:port (Integer. port-number)}))
  )
  









