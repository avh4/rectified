module Main where

import Rectified (..)
import Signal
import Window
import List

margin = 10

icon = image "http://placebear.com/80/80"

tabbar = right 40 margin icon (row 0 debug ["a", "b", "toolbar"]) |> grey 90

item n = left 60 0 (icon) (debug <| "Item " ++ toString n)

scene = top 40 margin tabbar (list 80 2 item [1..10])

main = Signal.map scene Window.dimensions
