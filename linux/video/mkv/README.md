# MKV

## mkvinfo

```
mkvinfo [options] <inname>

 Options:
  -a, --all                   Show all sub-elements (including cues & seek heads entries) and don't
                              stop at the first cluster.
  -c, --checksum              Calculate and display checksums of frame contents.
  -C, --check-mode            Calculate and display checksums and use verbosity level 4.
  -o, --continue              Don't stop processing at the first cluster.
  -P, --positions             Show the position of each element in decimal.
  -p, --hex-positions         Show the position of each element in hexadecimal.
  -s, --summary               Only show summaries of the contents, not each element.
  -t, --track-info            Show statistics for each track in verbose mode.
  -x, --hexdump               Show the first 16 bytes of each frame as a hex dump.
  -X, --full-hexdump          Show all bytes of each frame as a hex dump.
  -z, --size                  Show the size of each element including its header.
  -v, --verbose               Increase verbosity.
  -q, --quiet                 Suppress status output.
  --ui-language <code>        Force the translations for 'code' to be used.
  --command-line-charset <charset>
                              Charset for strings on the command line
  --output-charset <cset>     Output messages in this charset
  -r, --redirect-output <file>
                              Redirects all messages into this file.
  --flush-on-close            Flushes all cached data to storage when closing a file opened for
                              writing.
  --abort-on-warnings         Aborts the program after the first warning is emitted.
  @option-file.json           Reads additional command line options from the specified JSON file
                              (see man page).
  -h, --help                  Show this help.
  -V, --version               Show version information.

```
sample
```
mkvinfo 'Fullmetal.Alchemist.2017.BDRip(AVC).OlLanDGroup.mkv'
+ EBML head
|+ EBML version: 1
|+ EBML read version: 1
|+ Maximum EBML ID length: 4
|+ Maximum EBML size length: 8
|+ Document type: matroska
|+ Document type version: 4
|+ Document type read version: 2
+ Segment: size 2237317166
|+ Seek head (subentries will be skipped)
|+ EBML void: size 4012
|+ Segment information
| + Timestamp scale: 1000000
| + Multiplexing application: libebml v1.3.6 + libmatroska v1.4.9
| + Writing application: mkvmerge v25.0.0 ('Prog Noir') 64-bit
| + Duration: 02:13:47.520000000
| + Date: Sun Dec 02 16:38:07 2018 UTC
| + Title: Fullmetal.Alchemist.2017   #TorpedoUK#
| + Segment UID: 0x5f 0x33 0xa3 0x54 0x4a 0xad 0x91 0xcc 0xb6 0x5c 0x7f 0xdb 0x91 0x09 0x93 0xb0
|+ Tracks
| + Track
|  + Track number: 1 (track ID for mkvmerge & mkvextract: 0)
|  + Track UID: 1
|  + Track type: video
|  + Lacing flag: 0
|  + Codec ID: V_MPEG4/ISO/AVC
|  + Codec's private data: size 45 (H.264 profile: High @L4.1)
|  + Default duration: 00:00:00.041708333 (23.976 frames/fields per second for a video track)
|  + Video track
|   + Pixel width: 1024
|   + Pixel height: 436
|   + Display width: 1024
|   + Display height: 436

```
