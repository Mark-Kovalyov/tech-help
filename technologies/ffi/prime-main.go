package main

import "C"
import (
  "fmt"
  "unsafe"
)

func gcd(x,y) {
  res := C.gcd(x,y)
  return res
}

func main() {
  fmt.Println(string(gcd(32,18)))
}