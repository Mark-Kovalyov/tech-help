# Video processing


## Other tools:

### png2yuv

```
png2yuv -I p -f 24 -b 1 -n 1440 -j input.png > out.yuv
```

### mjpegtools

```
$ png2yuv
**ERROR: [png2yuv] png2yuv:  input format string not specified. (Use -j option.)
usage: png2yuv [ options ]

where options are ([] shows the defaults):
  -v num        verbosity (0,1,2)                  [1]
  -b framenum   starting frame number              [0]
  -f framerate  framerate for output stream (fps)     
  -n numframes  number of frames to process        [-1 = all]
  -j {1}%{2}d{3} Read PNG frames with the name components as follows:
               {1} PNG filename prefix (e g rendered_ )
               {2} Counting placeholder (like in C, printf, eg 06 ))
  -I x  interlacing mode:  p = none/progressive
                           t = top-field-first
                           b = bottom-field-first
  -L x  interleaving mode:  0 = non-interleaved (two successive
                                 fields per PNG file)
                            1 = interleaved fields
  -S mode  chroma subsampling mode [420jpeg]

png2yuv pipes a sequence of PNG files to stdout,
making the direct encoding of MPEG files possible under mpeg2enc.
Any 8bit PNG format supported by libpng can be read.
stdout will be filled with the YUV4MPEG movie data stream,
so be prepared to pipe it on to mpeg2enc or to write it into a file.


examples:
  png2yuv -j in_%06d.png -f 25 -I p -b 100000 > result.yuv
  | combines all the available PNGs that match
    in_??????.png, starting with 100000 (in_100000.png,
    in_100001.png, etc...) into the uncompressed YUV4MPEG videofile result.yuv
    The videofile has 25 frames per second and does not use any interlacing.
  png2yuv -Ip -L0 -j abc_%04d.png | mpeg2enc -f3 -o out.m2v
  | combines all the available PNGs that match
    abc_??????.png, starting with 0000 (abc_0000.png,
    abc_0001.png, etc...) and pipes it to mpeg2enc which encodes
    an MPEG2-file called out.m2v out of it

```
