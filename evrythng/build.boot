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
    (download :url "https://d10ka0m22z5ju5.cloudfront.net/toolkit/evrythng-js-sdk/evrythng-4.7.1.js" :checksum "395a3cb965fc382ef9ce25f6d98c946e")
    (download :url "https://d10ka0m22z5ju5.cloudfront.net/toolkit/evrythng-js-sdk/evrythng-4.7.1.min.js" :checksum "5ae5d1a8ba905d86cbc24f72f191b7e5")

    ;(show :fileset true)

    (sift :move {(re-pattern (str "evrythng-4\\.7\\.1\\.js$"))       "cljsjs/merge/development/evrythng.inc.js"})
    (sift :move {(re-pattern (str "evrythng-4\\.7\\.1\\.min\\.js$")) "cljsjs/merge/production/evrythng.min.inc.js"})

    (sift :include #{#"^cljsjs"})

    (deps-cljs :name "cljsjs.evrythng")
    (pom)
    (jar)))