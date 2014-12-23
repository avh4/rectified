module Rectified
  ( top, bottom, left, right
  , row, list
  , debug, text, image
  , grey
  ) where

import Text (..)
import Graphics.Element as G
import Graphics.Element (..)
import Color as C
import List

type alias Element = (Int,Int) -> G.Element

top : Int -> Int -> Element -> Element -> Element
top size spacing child1 child2 (w,h) =
  flow down
    [ child1 (w,size)
    , G.spacer w spacing
    , child2 (w,h-size-spacing)
    ]

bottom : Int -> Int -> Element -> Element -> Element
bottom size spacing child1 child2 (w,h) =
  flow down
    [ child1 (w,size)
    , G.spacer w spacing
    , child2 (w,h-size-spacing)
    ]

left : Int -> Int -> Element -> Element -> Element
left size spacing child1 child2 (w,h) =
  flow G.right
    [ child1 (size,h)
    , G.spacer spacing h
    , child2 (w-size-spacing,h)
    ]

right : Int -> Int -> Element -> Element -> Element
right size spacing child1 child2 (w,h) =
  flow G.right
    [ child2 (w-size-spacing,h)
    , G.spacer spacing h
    , child1 (size,h)
    ]

row : Int -> (a -> Element) -> List a -> Element
row spacing fn vs (w,h) =
  List.map (\v -> fn v (w // List.length vs,h)) vs
  |> List.intersperse (G.spacer spacing h)
  |> flow G.right

list : Int -> Int -> (a -> Element) -> List a -> Element
list rowSize spacing fn vs (w,h) =
  List.map (\v -> fn v (w,rowSize)) vs
  |> List.intersperse (G.spacer w spacing)
  |> flow G.down

debug : String -> Element
debug string (w,h) = asText string |> container w h middle

text : Style -> String -> Element
text st string (w,h) = string
  |> fromString
  |> style st
  |> leftAligned
  |> container w h middle

image : String -> Element
image url (w,h) = fittedImage w h url

grey : number -> Element -> Element
grey l child (w,h) = child (w,h) |> G.color (C.hsl 0 0 (l/100))
