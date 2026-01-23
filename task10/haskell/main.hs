{-# LANGUAGE OverloadedStrings #-}

import Web.Scotty
import Data.Aeson(FromJSON, ToJSON, object, (.=))
import Data.Text(Text)
import GHC.Generics (Generic)

data RequestBodyAddOrSubtract = RequestBodyAddOrSubtract
  { number1 :: Int
  , number2 :: Int
  , operationType :: Text
  } deriving (Show, Generic)

instance FromJSON RequestBodyAddOrSubtract
instance ToJSON RequestBodyAddOrSubtract

data RequestBodyIsEvenNumber = RequestBodyIsEvenNumber
  { number :: Int
  } deriving (Show, Generic)

instance FromJSON RequestBodyIsEvenNumber
instance ToJSON RequestBodyIsEvenNumber

data RequestBodyPower = RequestBodyPower
  { base :: Int
  , expo :: Int
  } deriving (Show, Generic)

instance FromJSON RequestBodyPower
instance ToJSON RequestBodyPower

main :: IO ()
main = scotty 3000 $ do
  post "/calculate" $ do
    body <- jsonData :: ActionM RequestBodyAddOrSubtract
    
    let result = case operationType body of
          "add" -> fmap (\(a, b) -> a + b) (Just (number1 body, number2 body))
          "subtract" -> fmap (\(a, b) -> a - b) (Just (number1 body, number2 body))
          _ -> Nothing
    
    case result of
      Just res -> json $ object ["result" .= res]
      Nothing  -> json $ object ["error" .= ("Accepted operation types are 'add' and 'subtract'" :: Text)]

  post "/isEvenNumber" $ do
    body <- jsonData :: ActionM RequestBodyIsEvenNumber
    
    let isEven = even (number body)
    json $ object ["isEven" .= isEven]

  post "/power" $ do
    body <- jsonData :: ActionM RequestBodyPower
    
    let result = (base body) ^ (expo body)
    json $ object ["result" .= result]