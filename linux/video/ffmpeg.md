# FFMpeg use cases

## Mix Video and Audio from different sources
```
ffmpeg -i "[Anime-Ancestors] Baki The Grappler S1_12.mka" \
       -i "[Anime-Ancestors] Baki The Grappler S1_12.mkv" \
       -c:v copy -c:a copy \
       -map 0:v:0 -map 1:a:0 \
       baki-s01-12.mp4
```

## Encode pictures 
```
ffmpeg -framerate -pattern_type glob -i '*.png' -c:v libx264 out.mp4
```

## Get video info
```
mediainfo input.mkv
```

## Encode video with scaling and audio/video codec

```
ffmpeg -i world-smallest-asm-win-app.webm -vcodec libx264 -s 1920x1080 -acodec libvorbis world-smallest-asm-win-app.mp4
```

## Encode audio only with keep video steam as is
```
ffmpeg -i .... -vcodec copy -acodec vorbis .....
```

## Re-map second audio-track
```
ffmpeg -i holmes-shadow-game.mkv -vcodec libx264 -s 816x340 -map 0:a:1 -acodec mp3 holmes-shadow-game-a1.mp4
```

## Cut specific scene from video. Keep audio/video formats as is
```
ffmpeg -i eneida-1991.mp4 -ss 00:12:00 -to 00:14:10 -c:v copy -c:a copy eneida-1991-cut-01.mp4
```

## Change gamma
```
ffmpeg -i vir-lin.mp4 -vf eq=gamma=1.5:saturation=1.3 -c:a copy vir-lin-gamma-1-5.mp4
```

## Create animated gifs
```
ffmpeg -i %04d.png -s 288x162 test.gif
```