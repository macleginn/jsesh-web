# JSesh Web

JSesh Web is a web wrapper for the Egyptian hieroglyphic editor and hieroglyph rendering library [JSesh](https://jsesh.qenherkhopeshef.org/) created by [Serge Rosmorduc](http://www.qenherkhopeshef.org/sergeRosmorduc).

JSesh Web is a simple web app written in Clojure and based on `Jetty` web server with `Ring` middleware. The app takes [Manuel de Codage](http://www.catchpenny.org/codage/) transliteration as input via http requests and returns a base64-encoded png image. The working implementation of the app is deployed to Heroku: [https://pure-sierra-27254.herokuapp.com/](https://pure-sierra-27254.herokuapp.com/) An example usage is `https://pure-sierra-27254.herokuapp.com/?mdc=<p:t-H>` (with URI components escaped accordingly).

An example client web-page encapsulating the output into an `<img>` tag can be found at [http://eurasianphonology.info/static/egyptcl/mdctest.html](http://eurasianphonology.info/static/egyptcl/mdctest.html).