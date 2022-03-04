;;; Coding Art example 3.1.1
;;; Use a record to hold particle data.
(ns core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defrecord Particle [x y size x-dir y-dir])

(defn- initialise
  "Initialise the programs's state with 400 particles."
  []
  (q/smooth)
  (q/no-stroke)
  (repeatedly 4000 #(->Particle 0 0 (q/random 0.5 4) (q/random -1 1) (q/random -1 1))))

(defn- draw-particle
  "Draw a particle."
  [{:keys [x y size]}]
  (q/fill 238 120 138 (* size 30))
  (q/ellipse x y size size))

(defn- move-particle
  "Move a particle."
  [{:keys [x y x-dir y-dir]
    :as   particle}]
  (assoc particle :x (+ x x-dir) :y (+ y y-dir)))

(defn- draw
  "Draw all the particles."
  [state]
  (q/background 35 27 107)
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
    (dorun (map draw-particle state))))

(defn- update-state
  "Move all the particles."
  [state]
  (map move-particle state))

(q/defsketch sketch
  :title "3.1.1"
  :setup initialise
  :update update-state
  :draw draw
  :middleware [m/fun-mode]
  :size [900 900])