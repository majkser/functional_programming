{-# LANGUAGE OverloadedStrings #-}

import Web.Scotty
import Data.Aeson(FromJSON, ToJSON, object (.=))
import Data.Text(Text)

main :: IO ()
main = do