module Rectified
  ( top, bottom, left, right
  , row, list
  , debug, image
  , grey
  ) where

import Text (..)
import Graphics.Element as G
import Graphics.Element (..)
import Color as C
import List

type alias Element = (Int,Int) -> G.Element

top : Int -> Element -> Element -> Element
top size child1 child2 (w,h) =
  flow down [ child1 (w,size), child2 (w,h-size) ]

bottom : Int -> Element -> Element -> Element
bottom size child1 child2 (w,h) =
  flow down [ child1 (w,size), child2 (w,h-size) ]

left : Int -> Element -> Element -> Element
left size child1 child2 (w,h) =
  flow G.right [ child1 (size,h), child2 (w-size,h) ]

right : Int -> Element -> Element -> Element
right size child1 child2 (w,h) =
  flow G.right [ child2 (w-size,h), child1 (size,h) ]

row : List Element -> Element
row children (w,h) =
  List.map (\child -> child (w // List.length children,h)) children
  |> flow G.right

list : Int -> (a -> Element) -> List a -> Element
list rowSize fn vs (w,h) =
  List.map (\v -> fn v (w,rowSize)) vs
  |> flow G.down

debug : String -> Element
debug string (w,h) = asText string |> container w h middle

image : String -> Element
image url (w,h) = fittedImage w h url

grey : number -> Element -> Element
grey l child (w,h) = child (w,h) |> G.color (C.hsl 0 0 (l/100))
