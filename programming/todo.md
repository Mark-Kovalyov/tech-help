# TODO: 
* Engine
  * MBE - mtns block engine
* MCube - multidimensional fact storage
* CLI for block backup (bbackup)
  * Variable Block size
  * Merkle-tree as index
* CLI for logfiles searh (lfsearch)
  * tri-gramm indexing
  * partitioning by date
* CLI for binary file replacements (breplace)
  * replace bytes 'ff ef' on 'da cb'
* CLI for text replacement (treplace)
* CLI for audio-modem (aencode)
* CLI for Radix-compressor
* TankIGo 
* Datastructures
  * 2Q-Cache
  * Heap
  * Rope(Cord)
  * Ternary Tree
  * RadixTree
* Emulators
  * x86
  * ARM


## Emulators

### x86

```
trait Register

class Int512Reg {
   var v : BigInteger = 0

}

class CPU {
 
   var zmm0 : Int512Reg
   var zmm1 : Int512Reg

   def VBROADCASTSS(m : MemoryPointer, r : AVX512) : Unit = ???
   // SSE / FPU
   def movs() : Unit = ???
   def mulps() : Unit = ???
   def movaps(r : SSE, m : MemoryPointer) = ???
   def addps(r : SSE, m : MemoryPointer) = ???
   def movaps(m : MemoryPointer, r : SSE) = ???
}

enum AVX512 {
   ZMM0, ZMM1 ... ZMM31
}

enum AVX {
   YMM0,.... YMM15
}

enum SSE {
   XMM0,....XMM15
}

```

## Languages

* Rust
* ZigLang
* Nim (application, general, web, scripting, system)
* Carbon

* Scheme
* Haskell

## Features

|Feature      |Rust        |ZigLang    |Nim        | Carbon |
|-------------|------------|-----------|-----------|--------|
|Imperative   |  +         |  +        |  +        |
|OO           |  +         |           |  +        |
|Functional   |  +         |  +        |  +        |
|Procedural   |  +         |  +        |  +        |
|Generic      |  +         |  +        |  +        |
|Reflective   |            |  +        |  +        |
|Event driven |  +         |  +        |           |
|Other        |Concurrent  |Concurrent |Multiple   |
|             |            |           |dispatch   |
|Standartized |            |           |           |


### Metaprogramming



## Bbackup
```
$ bbackup [src] [dest]

1) Create SRC-merkle tree (MT)
2) Sync with DEST-MT
3) Sync src => dest only changed blocks

Others:
- track MT for every file?
- group small files?
- exclude from MT some non-block files like *.json, *.xml 
```

## lfsearch

.lfsearch/config.yaml
```
nginx:
 dirs: "yyyy/MM/dd/hh"
 date-format: "yyyy-MM-dd hh:mm:ss,FFF"
 levels: [ info, warn, error ]
---
tomcat:
  
```

```
$ lfsearch index [src] --profile nginx 
....

$ lfsearch search [src] --profile nginx --since today
$ lfsearch search [src] --profile nginx --since 2021-01-01

```

## Breaplace
```

```

## Treaplace

```
$ cat .treplace/config.yaml
---
months:
 word-replacement:
   source: [ "jan", "feb" ]
   dest: [ "янв", "фев" ]
---
roman:
 word-replacement:
   source: [ "1","2" ]
   dest: [ "I", "II" ]



$ treaplace roman file.txt
```

## Audio Modem (aencode)

### Terms

* (A)PSK = round
* QAM = square
* MSE : Mean Square Error
* intersymbol interference (ISI) 

* Modulation:
  * QAM
    * 16-PSK
    * 16-APSK (4 bits/symbol)
    * 32-APSK (5 bits/symbol)
    * 16-PSK
    * 16-QAM,32-QAM
* Modes:
  * mono
  * stereo
  * 16 bit
  * 8 bit
* Frequency
  * 44100
  * 22050
  * 11025 

Example of usage:
* DBV-S
  S2 : 16-APSK,32-APSK
  S2X : 64/128/256-APSK

```
$ aencode input.data output.wav
```

### What to do to recover info

* Automatic Gain Control(авто регулировка усиления) 
  * Optimal position of constellation diagram in reception window (оптимальное положение диаграммы созвездия 
    в окне приема
* Quadrature down conversion (квадратурное преобразование с понижением частоты)
* Nyquist Filtering (фильтр Найквиста)
  * Pulse shaping (формирование импульса)
* Clock Recovery (восстановление часов) 
  * Sampling reference for A/D Converter (справочник для выборки АЦП)
* Carrier Recovery 
  * Carrier frequency reference
* Adaptive Equaliser 
  * Compensate for channel distortion
* Demapping 
  * Representation of received data in bit



### Dot product

```
movups xmm1, [rsi]
movups xmm2, [rdi]
mulps xmm1, xmm2
addps xmm0, xmm1
```

```
struct {
 float a; // 32bit
 float b; // 32bit
}
```

### Sox utility

Genetate sine tone with 260Hz frequency and 8K sampling and 15s length
```
sox -n -r 8000 output.wav synth 15 sine 260
```

Convert raw file to wave
```
```

### Phone modem protocols

|Connection              |Modulation|Bit rate  | Year |
|------------------------|----------|----------|------|
|V92 (56 kbit/s)         | digital  |56 kbit/s | 2000 |
|V34                     | trellis  |33 kbit/s | 1996 |
|4,800 bit/s (1600 baud) | DPSK     |4.8 kbit/s|      |
|V.22bis                 | QAM      |2.4 kbit  | 1984 |
|V.22                    | QPSK     |1.2 kbit  | 1980 |


### ZX-Spectrum tape protocol

48k / 5 min = 160 bytes/sec

A stream of pure '0' bits loads at 3500000 ÷ 1710 ≈ 2046 baud
A stream of pure '1' bits loads at 3500000 ÷ 3420 ≈ 1023 baud
A mixed stream with equal numbers of '0' and '1' bits loads at 2 × 3500000 ÷ (3420 + 1710) ~ 1364 baud 


## URL list compression


```
https://
 +- com
     +- stackoverflow
          questions
              5928156/replace-one-character-with-another-in-bash
              53672192/replace-hyphens-with-underscores-in-bash-script?noredirect=1&lq=1
     +- askubuntu
           +- questions
                 1454878/which-kernel-version-to-delete-when-low-disk-space-on-boot
                 280211/how-do-i-resize-my-boot-partition?noredirect=1&lq=1
```

## History of LLVM-supported modern languages

|Language|Created|First Released|Ver   |
|--------|-------|--------------|------|
|Rust    |       |April 15, 2015|1.68.0|
|Zig-Lang|2015   |              |0.10.1|
|Nim-Lang|
|Julia   |

## Rope (Cord)

## Ternary Tree

* 3 child
* 32х bit pointers

64 bytes - (4bytes * 3pointers)

```
class TerTree[T] {
  var left :Option[TerTree[T]]
  var mid  :Option[TerTree[T]]
  var right:Option[TerTree[T]]
  var value:T
}
```

```
p1 p1 p1 p1 p2 p2 p2 p2 p3 p3 p3 p3 

```