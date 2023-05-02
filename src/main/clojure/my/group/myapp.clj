(ns my.group.myapp
  (:require [cljfx.api :as fx]
            [my.group.component-wrappers :as comp-wrappers])
  (:import [javafx.application Platform])
  (:gen-class))

(defonce *state (atom {:slider-value 40.0}))

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

(defn java-info-label [& args]
  {:fx/type :label
   :text (str "Hello, JavaFx "
              (System/getProperty "javafx.version") 
              " with cljfx, running on Java " 
              (System/getProperty "java.version") 
              ".")})

(defn root-view[{{:keys [slider-value]} :state}]
  {:fx/type :stage
   :showing true
   :width 640
   :height 480
   :scene {:fx/type :scene
           :root {:fx/type :v-box
                  :alignment :center
                  :padding 10
                  :spacing 10
                  :children [{:fx/type java-info-label}
                             {:fx/type comp-wrappers/slider-label
                              :text (format "%.2f" slider-value)}
                             {:fx/type slider-view
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
