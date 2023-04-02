{-# LANGUAGE ForeignFunctionInterface #-}
module Prime where

import Foreign
import Foreign.C.Types

foreign import ccall "gcd" c_gcd :: CLong -> CLong -> CLong

