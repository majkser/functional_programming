{-# LANGUAGE OverloadedStrings #-}

import Web.Scotty
import Data.Aeson(object, (.=))
import Data.Text(Text)

main :: IO ()
main = scotty 3000 $ do
