(ns anim-sandbox.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:color 0
   :it 1})

(defn update-state [state]
   {:color (mod (+ (:color state) 0.7) 255)
   :it (+ 2 (:it state))})

(defn draw-state [state]
  (q/background 0)
  (q/stroke (:color state) 255 255)
  (q/stroke-weight 5)
  (q/no-fill)

  (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
  ; Draw the circle.
  (doseq [ x (range 0 500 50) ] 
  (q/ellipse 0 0 (+ x (rem (:it state) 700)) (+ x (rem (:it state) 700)))
  (q/ellipse 0 0 (- x (rem (:it state) 700)) (- x (rem (:it state) 700)))
  (q/ellipse 0 0 (- (+ x 50) (rem (:it state) 700)) (- (+ x 75) (rem (:it state) 700)))
  (q/ellipse 0 0 (- (+ x 75) (rem (:it state) 700)) (- (+ x 50) (rem (:it state) 700)))
    ))
  
)

(q/defsketch anim-sandbox
  :title "whoa, dude"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
