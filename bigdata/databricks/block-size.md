# Block size

| x                  | Blob Storage | DBFS   | Hadoop / HDFS |
|--------------------|--------------|--------|---------------|
|Minimal block size  |  64k..100Mb  |   ?    |    128M       |
|Maximum file size   |


https://kb.databricks.com/en_US/execution/increase-tasks-per-stage#:~:text=When%20data%20is%20read%20from,128%20MB%20(128000000%20bytes).

Check the 

spark config:
 spark.hadoop.mapred.max.split.size = 32 000 000
