(ns my-exercise.search
  (:use compojure.core)
  (:require [hiccup.page :refer [html5]]
            [clojure.tools.logging :as log]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [my-exercise.us-state :as us-state]
            [clojure.edn :as edn]
            [clj-http.client :as client]))

(defn get-elections
  "get elections by city and state"
  [request]
  (def state ((request :params) :state))
  (def place ((request :params) :city))
  ()
  (client/get 
  (str "https://api.turbovote.org/elections/upcoming?district-divisions=ocd-division/country:us/state:" 
        (clojure.string/lower-case state)
        "/place:"
        (clojure.string/lower-case place))))

; (defn parse-elections [request]
;   [:div {:class "parsed-elections"}
;    (get-elections request)])

(defn page [request]
  (html5
    (get-elections request)
  ;  (parse-elections request)
   ))