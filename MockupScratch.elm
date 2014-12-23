module Main where

import Rectified (..)
import Signal
import Window
import List
import Text
import Color as C
import Html

item3 = Html.node "div" []
  [ Html.node "h2" [] [ Html.text "Item 3" ]
  , Html.node "p" [] [ Html.text "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." ]
  ]

margin = 8

icon = image "http://placebear.com/80/80"

highlight = grey 70
background = grey 85
panel = grey 98

plain = Text.defaultStyle
bold =  { plain | bold <- True }

tab (isHighlight, string) = case isHighlight of
  True -> centeredText bold string |> highlight
  False -> centeredText plain string

tabbar = row 0 tab [(True,"Scratch"), (False,"Tasks"), (False,"Notes")]
  |> panel

item n = case n of
  3 -> empty
  _ -> (text plain margin <| "Item " ++ toString n) |> panel

navbar = (list 60 2 item [1..36])

content = html margin (item3) |> grey 100

scene = top 60 0
  tabbar
  (left 280 margin
    navbar
    content |> inset margin)
  |> background


main = Signal.map scene Window.dimensions
