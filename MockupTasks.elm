module Main where

import Rectified (..)
import Signal
import Window
import List
import Text
import Color as C
import Html

margin = 8

highlight = grey 70
background = grey 85
panel = grey 98

plain = Text.defaultStyle
bold =  { plain | bold <- True }

tab (isHighlight, string) = case isHighlight of
  True -> centeredText bold string |> highlight
  False -> centeredText plain string

tabbar = row 0 tab [(False,"Scratch"), (True,"Tasks"), (False,"Notes")]
  |> panel

item n = case n of
  3 -> (centeredText plain <| "Item " ++ toString n) |> highlight
  _ -> (centeredText plain <| "Item " ++ toString n) |> panel

navbar = row 2 item [1..3]
  |> rotateLeft

content =
  debug "right"
  |> left 300 0 (debug "left" |> grey 97)
  |> grey 100

scene = content
  |> left 30 margin navbar
  |> inset margin
  |> top 60 0 tabbar
  |> background


main = Signal.map scene Window.dimensions
