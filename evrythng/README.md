# cljsjs/evrythng

[](dependency)
```clojure
[cljsjs/evrythng "4.7.1"] ;; latest release
```
[](/dependency)

See [evrythng.js](https://github.com/evrythng/evrythng.js/) and [evrythng.com](https://evrythng.com/).

This jar comes with `deps.cljs` as used by the [Foreign Libs][flibs] feature
of the ClojureScript compiler. After adding the above dependency to your project
you can require the packaged library like so:

```clojure
(ns application.core
  (:require cljsjs.evrythng))
```

## Build

To build with boot, and install locally:

    boot package install

[flibs]: https://github.com/clojure/clojurescript/wiki/Packaging-Foreign-Dependencies
