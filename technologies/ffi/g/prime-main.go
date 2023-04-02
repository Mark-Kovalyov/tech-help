package main

/*
extern long gcd(long a,long b);
*/

import "C"
import (
  "fmt"
  _ "unsafe"
)


func gcd_native(x uint64,y uint64) uint64 {
  res := C.gcd(x,y)    
  return res
} 

/*
func gcd(x uint64,y uint64) uint64 {
   if y != 0 {
      return gcd(y, x % y) 
   } else {
      return x
   }
}*/

func main() {
  fmt.Printf("gcd 32,18 = %d \n", gcd_native(6,15))
}