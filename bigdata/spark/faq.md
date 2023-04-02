# Spark FAQ


## Get hadoop context from Spark context
```
scala> :paste
// Entering paste mode (ctrl-D to finish)

    spark.sparkContext
      .hadoopConfiguration
      .iterator()
      .asScala
      .map(mapEntry => (mapEntry.getKey,mapEntry.getValue))
      .filter(pair => pair._1.contains("ftp"))
      .toList
      .sortBy(pair => pair._1)
      .foreach(x => println(s"${x._1} = ${x._2}"))


// Exiting paste mode, now interpreting.

fs.AbstractFileSystem.ftp.impl = org.apache.hadoop.fs.ftp.FtpFs
fs.ftp.data.connection.mode = ACTIVE_LOCAL_DATA_CONNECTION_MODE
fs.ftp.host = 0.0.0.0
fs.ftp.host.port = 21
fs.ftp.impl = org.apache.hadoop.fs.ftp.FTPFileSystem
fs.ftp.timeout = 0
fs.ftp.transfer.mode = BLOCK_TRANSFER_MODE
fs.viewfs.overload.scheme.target.ftp.impl = org.apache.hadoop.fs.ftp.FTPFileSystem
ftp.blocksize = 67108864
ftp.bytes-per-checksum = 512
ftp.client-write-packet-size = 65536
ftp.replication = 3
ftp.stream-buffer-size = 4096
```

## Set Hadoop property
```
spark.hadoop.fs.s3a.access.key=11112222333
```

## Generate dataframe with sequence of nums
```
val nums = spark.range(1, 1000000)
```

## Run spark-shell from docker
```
docker run -it --rm apache/spark /opt/spark/bin/spark-shell
```

# Select columns from dataframe
```
df.select("*").show()
val columnsAll=df.columns.map(m=>col(m))
df.select(columnsAll:_*).show()
df.select(columns.map(m=>col(m)):_*).show()

```
# Select columns by posision
```
//Selects 4th column (index starts from zero)
df.select(df.columns(3)).show()
//Selects columns from index 2 to 4
df.select(df.columns.slice(2,4).map(m=>col(m)):_*).show()

```

# Cast
```

import org.apache.spark.sql.types.DoubleType

df.withColumn("salary",col("salary").cast(DoubleType))

```

# Create table based on DataFrame

```
df.createOrReplaceTempView("df")
```

# Check field is null
```
df.filter("state is NULL").show(false)
df.filter(df("state").isNull).show(false)
df.filter(col("state").isNull).show(false) /
```

# Create Df based on Databricks table
```


```

## Create Delta table from Dataframe
```

```

## Create empty Dataframe

```
case class User(name:String,age:Int)
```

```
val df = spark.emptyDataFrame
df.show
```

```
val df = spark.createDataFrame(spark.sparkContext.emptyRDD[Row], schema)
```

```
val ds = spark.emptyDataset[User]
ds.show
```

## From JDBC
```

val df_mysql = spark.read.format(“jdbc”)
   .option(“url”, “jdbc:mysql://localhost:port/db”)
   .option(“driver”, “com.mysql.jdbc.Driver”)
   .option(“dbtable”, “tablename”) 
   .option(“user”, “user”) 
   .option(“password”, “password”) 
   .load()

```


## From HBase
```

    val hbaseDF = sparkSession.read
      .options(Map(HBaseTableCatalog.tableCatalog -> catalog))
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()

```


## Q: Unable to find encoder for type X. An implicit Encoder[X] is needed to store X instances in a Dataset.

```
val ratingsString : Dataset[String] = spark.read.textFile("data/mllib/als/sample_movielens_ratings.txt")
    val ratings : Dataset[Rating] = ratingsString.map(x => parseRating(x)).toDF()
```
```
Unable to find encoder for type mayton.als.ml.Rating.
An implicit Encoder[mayton.als.ml.Rating] is needed
to store mayton.als.ml.Rating instances in a Dataset.
Primitive types (Int, String, etc) and Product types
(case classes) are supported by importing spark.implicits._  
Support for serializing other types will be added in future

```

Answer:
