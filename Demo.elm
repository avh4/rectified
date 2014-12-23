module Main where

import Rectified (..)
import Signal
import Window
import List

icon = image "http://placebear.com/80/80"

tabbar = right 40 icon (row [debug "a", debug "b", debug "toolbar"]) |> grey 90

item n = left 60 (icon) (debug <| "Item " ++ toString n)

scene = top 40 tabbar (list 80 item [1..10])

main = Signal.map scene Window.dimensions
