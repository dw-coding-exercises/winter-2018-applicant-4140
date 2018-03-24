(ns my-exercise.search
  (:use compojure.core)
  (:require [hiccup.page :refer [html5]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [my-exercise.us-state :as us-state]
            [clojure.edn :as edn]
            [clj-http.client :as client]))

(defn get-elections [_]
  "Gets elections for search (currently just for bonifay, fl)"
  (client/get 
  "https://api.turbovote.org/elections/upcoming?district-divisions=ocd-division/country:us/state:fl/place:bonifay"))

(defn page [request]
  (html5
   (get-elections request)))