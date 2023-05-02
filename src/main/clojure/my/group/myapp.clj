(ns my.group.myapp
  (:require [cljfx.api :as fx])
  (:import [javafx.application Platform])
  (:gen-class))

(defonce *state (atom {:slider-value 50}))

(defmulti event-handler :event/type)

(defmethod event-handler ::set-slider-value [e]
  (swap! *state assoc :slider-value (:fx/event e)))

(defn slider-view [{:keys [min max value event]}]
  {:fx/type :slider
   :min min
   :max max
   :value value
   :major-tick-unit max
   :on-value-changed {:event/type event}
   :show-tick-labels true})

(defn root-view[{{:keys [slider-value]} :state}]
  {:fx/type :stage
   :showing true
   :scene {:fx/type :scene
           :root {:fx/type :h-box
                  :spacing 10
                  :children [{:fx/type slider-view
                              :min 0
                              :max 100
                              :event ::set-slider-value
                              :value slider-value}]}}})

(def renderer
  (fx/create-renderer
   :middleware (fx/wrap-map-desc (fn [state]
                                   {:fx/type root-view
                                    :state state}))
   :opts {:fx.opt/map-event-handler event-handler}))

(defn -main
  [& args]
  (Platform/setImplicitExit true)
  (fx/mount-renderer *state renderer))
