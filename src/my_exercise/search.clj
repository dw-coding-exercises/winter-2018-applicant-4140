(ns my-exercise.search
  (:use compojure.core)
  (:require [hiccup.page :refer [html5]]
            [clojure.tools.logging :as log]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [my-exercise.us-state :as us-state]
            [clojure.edn :as edn]
            [clj-http.client :as client]))

(defn get-elections
  "get unparsed elections by city and state"
  [request]
  (def state ((request :params) :state))
  (def place ((request :params) :city))
  ()
  (client/get 
  (str "https://api.turbovote.org/elections/upcoming?district-divisions=ocd-division/country:us/state:" 
        (clojure.string/lower-case state)
        "/place:"
        (clojure.string/lower-case place))))

(defn render-election [election]
  [:ul
    [:li (election :description)]
    [:li (election :date)]
    [:li (election :polling-place-url-shortened)]]
  )

(defn parse-elections [unparsed-elections]
  (def raw-elections (unparsed-elections :body))
  (def elections (edn/read-string raw-elections))
  [:div {:class "election"}
    (map render-election elections)]
  )

(defn page [request]
  (html5
    (parse-elections (get-elections request))
   ))