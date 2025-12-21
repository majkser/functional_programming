{-# LANGUAGE OverloadedStrings #-}

import Web.Scotty
import Data.Aeson(ToJSON, FromJSON, object, (.=))
import Data.Text(Text)
import GHC.Generics(Generic)

data RequestBody = RequestBody
  { values :: [Int]
  } deriving (Show, Generic)

instance FromJSON RequestBody
instance ToJSON RequestBody

isSorted :: [Int] -> Text -> Bool
isSorted [] _ = True
isSorted [_] _ = True
isSorted lst order = case order of
  "ascending"  -> and $ zipWith (<=) lst (tail lst)
  "descending" -> and $ zipWith (>=) lst (tail lst)
  _      -> False

main :: IO ()
main = scotty 3000 $ do

  post "/isSorted/:order" $ do
    body <- jsonData :: ActionM RequestBody
    order <- param "order" :: ActionM Text
    let sorted = isSorted (values body) order
    json $ object ["are values sorted: " .= sorted]
