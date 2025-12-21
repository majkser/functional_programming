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

data RequestBodyThreeLists = RequestBodyThreeLists
  { list1 :: [Int]
  , list2 :: [Int]
  , list3 :: [Int]
  } deriving (Show, Generic)

instance FromJSON RequestBodyThreeLists
instance ToJSON RequestBodyThreeLists

isSorted :: [Int] -> Text -> Bool
isSorted [] _ = True
isSorted [_] _ = True
isSorted lst order = case order of
  "ascending"  -> and $ zipWith (<=) lst (tail lst)
  "descending" -> and $ zipWith (>=) lst (tail lst)
  _      -> False

sumTwoLists :: [Int] -> [Int] -> [Int]
sumTwoLists = zipWith (+)

sumThreeLists :: [Int] -> [Int] -> [Int] -> [Int]
sumThreeLists a b c = (sumTwoLists (sumTwoLists a b) c)

main :: IO ()
main = scotty 3000 $ do

  post "/isSorted/:order" $ do
    body <- jsonData :: ActionM RequestBody
    order <- param "order" :: ActionM Text
    let sorted = isSorted (values body) order
    json $ object ["are values sorted: " .= sorted]

  post "/sumThreeLists" $ do
    body <- jsonData :: ActionM RequestBodyThreeLists
    let summedList = sumThreeLists (list1 body) (list2 body) (list3 body)
    json $ object ["summed list: " .= summedList]