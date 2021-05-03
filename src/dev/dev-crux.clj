(ns dev.dev-crux
  (:require
   [clojure.spec.alpha :as s]
   [clojure.tools.namespace.repl :as tools-ns]
   [expound.alpha :as expound]
   [mount.core :as mount]
   [shadow.cljs.devtools.api :as shadow]
   [callum-herries.fulcrux.server.db-layer :as db]
   ;; this is the top-level dependent component...mount will find the rest via ns requires
   [callum-herries.fulcrux.server.server :refer [http-server]]
   [callum-herries.fulcrux.server.crux-node :refer [crux-node]]))

;; This namespace is just for exploring the crux database with ad-hoc queries

(comment
  (db/get-all-tasks crux-node)

  )