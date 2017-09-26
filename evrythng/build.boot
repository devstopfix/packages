(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.7.1" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "4.7.1")
(def +version+ (str +lib-version+))

(task-options!
 pom  {:project     'cljsjs/evrythng
       :version     +version+
       :description "EVRYTHNG.com REST API client"
       :url         "https://github.com/evrythng/evrythng.js"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"Apache License 2.0" "https://github.com/evrythng/evrythng.js/blob/master/LICENSE.txt"}})

(require '[boot.core :as c]
         '[boot.tmpdir :as tmpd]
         '[clojure.java.io :as io]
         '[clojure.string :as string])

(deftask package []
  (comp
    (download :url "https://raw.githubusercontent.com/evrythng/evrythng.js/673c6022600152c304dd923fed5569125c9e99a4/lib/evrythng.js" :checksum "5b200074d7347f66fcdafcc7724373d8")
    (download :url "https://raw.githubusercontent.com/evrythng/evrythng.js/673c6022600152c304dd923fed5569125c9e99a4/lib/evrythng.min.js" :checksum "e95e2b1ce5fa370dca79cad1316ef253")

    (sift :move {(re-pattern (str "evrythng\\.min\\.js$")) "cljsjs/merge/production/evrythng.min.inc.js"})
    (sift :move {(re-pattern (str "evrythng\\.js$")) "cljsjs/merge/development/evrythng.inc.js"})

    ; (minify :in "cljsjs/evrythng/development/evrythng.js"
    ;          :out "cljsjs/evrythng/production/evrythng.min.js")

    (sift :include #{#"^cljsjs"})

    (deps-cljs :name "cljsjs.evrythng")
    (pom)
    (jar)))