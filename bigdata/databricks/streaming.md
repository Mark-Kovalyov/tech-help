# Streaming

```
spark.readStream
 .schema(....)
 .option("maxFilesPerTrigger", 1)
 .parquet(.....)
 .filter(col("msg") == "fin")
 .groupBy("categor").count()
 .writeStream
   .outputMode("append")
   .format("parquet")
   .queryName("ratings")
   .trigger(processingTime = "15 sec")
   .option("checkpointLocation", .....)
   .start(.....)
```
