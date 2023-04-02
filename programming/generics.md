# Generics in Rust/Haskell/Nim/Zig/Cargo

# Minimal of generic objects

## Zig
```
const std = @import("std");

fn mmin(x : anytype, y : anytype) anytype {
    
}
```

## Nim
```
proc mmin[T](x,y:T) : T = if x < y : x else: y
```
```
mmin(1,2) = 1
mmin(3.1,3.2) = 3.1
mmin('a','b') = a
```

## Rust
```
fn mmin<T>(x: T, y: T) { 
   if x < y {
     return x
   } else {
     return y
   } 
}
```

## Scala
```
import math.Ordering

def mmin[T](x:T,y:T)(implicit ord:Ordering[T]) : T = if (ord.lt(x,y)) x else y
```
