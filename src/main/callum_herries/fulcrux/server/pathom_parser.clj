(ns callum-herries.fulcrux.server.pathom-parser
  (:require
    [com.wsscode.pathom.connect :as pc]
    [dv.pathom :refer [build-parser]]
    [mount.core :refer [defstate]]
    [taoensso.timbre :as log]
    [callum-herries.fulcrux.server.task-resolvers :as task]
    [callum-herries.fulcrux.auth.session :as session]
    [callum-herries.fulcrux.auth.user :as user]
    [callum-herries.fulcrux.server.config :refer [config]]
    [callum-herries.fulcrux.server.crux-node :refer [crux-node]]))

(def all-resolvers
  [session/resolvers
   user/resolvers
   task/resolvers])

(defstate parser
  :start
  (let [{:keys [log-responses? trace? index-explorer? connect-viz?]} (::config config)]
    (log/info "Constructing pathom parser with config: " (::config config))
    (build-parser
      {:resolvers          all-resolvers
       :log-responses?     log-responses?
       :handle-errors?     true
       :trace?             trace?
       :index-explorer?    index-explorer?
       :enable-pathom-viz? connect-viz?
       :env-additions      (fn [env]
                             {:crux-node    crux-node
                              :config       config
                              :current-user (user/get-current-user env)})})))
