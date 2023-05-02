(ns my.group.component-wrappers
  (:require [cljfx.composite :as composite]
            [cljfx.lifecycle :as lifecycle]
            [cljfx.fx.label :as fx.label])
  (:import [components SliderLabel]))

(def slider-label
  (lifecycle/annotate
   (composite/describe SliderLabel
                       :ctor []
                       :props fx.label/props)
   :slider-label))
