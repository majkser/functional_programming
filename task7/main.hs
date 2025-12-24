{-# LANGUAGE OverloadedStrings #-}

import Web.Scotty
import Data.Aeson(object, (.=))
import Data.Text(Text)
import Control.Monad.State
import Data.IORef
import Control.Monad.IO.Class (liftIO)

nextRandomInt :: State Int Int
nextRandomInt = do
  currSeed <- Control.Monad.State.get
  let
    -- values taken from c++11's std::minstd_rand
    a = 48271
    c = 0
    m = (2 ^ 31) - 1
    nextSeed = (a * currSeed + c) `mod` m
  Control.Monad.State.put nextSeed
  return nextSeed

nextRandomDouble :: State Int Double
nextRandomDouble = do
  randInt <- nextRandomInt
  let m = (2 ^ 31) - 1
  return $ fromIntegral randInt / fromIntegral m

bridge :: IORef s -> State s a -> IO a
bridge ref stateAction = 
  atomicModifyIORef ref $ \currentState ->
    let (result, newState) = runState stateAction currentState
    in (newState, result)

main :: IO ()
main = do
  stateSeed <- newIORef 12345

  scotty 3000 $ do
    Web.Scotty.get "/random-int" $ do
      randomInt <- liftIO $ bridge stateSeed nextRandomInt
      json $ object ["random int: " .= randomInt]

    Web.Scotty.get "/random-double" $ do
      randomDouble <- liftIO $ bridge stateSeed nextRandomDouble
      json $ object ["random double: " .= randomDouble]