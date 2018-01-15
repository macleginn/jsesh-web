# JSesh Web

JSesh Web is a web wrapper for the Egyptian hieroglyphic editor and hieroglyph rendering library [JSesh](https://jsesh.qenherkhopeshef.org/) created by [Serge Rosmorduc](http://www.qenherkhopeshef.org/sergeRosmorduc).

JSesh Web is a simple web app written in Clojure and based on `Jetty` web server with `Ring` middleware. The app takes [Manuel de Codage](http://www.catchpenny.org/codage/) transliteration as input via http requests and returns a base64-encoded png image. The working implementation of the app is deployed to Heroku: [https://pure-sierra-27254.herokuapp.com/](https://pure-sierra-27254.herokuapp.com/) An example usage is `https://pure-sierra-27254.herokuapp.com/?mdc=<p:t-H>` (with URI components escaped accordingly). Optional parameters include `height` of the output image and `centered=true`, which enables centered vertical alignment of small signs (by default they are aligned to the bottom).

An example client web-page encapsulating the output into an `<img>` tag can be found at [http://eurasianphonology.info/static/egyptcl/mdctest.html](http://eurasianphonology.info/static/egyptcl/mdctest.html).

You can run the app locally by executing

```bash
git clone https://github.com/macleginn/jsesh-web.git
cd jsesh-web
lein run -m hieroglyphs.web [optional-port-number]
```

The default port number is 5000.

Alternatively, compile the project with `lein uberjar` and run it with `java -cp YOUR_PATH/hieroglyphs-standalone.jar clojure.main -m hieroglyphs.web`.