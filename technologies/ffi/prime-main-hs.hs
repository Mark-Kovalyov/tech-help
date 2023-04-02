module Main where

import Prime

main :: IO ()
main = do print $ "gcd(12,16) = " ++ show (gcd 12 16)
