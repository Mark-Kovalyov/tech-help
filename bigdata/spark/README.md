# Spark

## Links

* https://spark.apache.org/
* https://sparkbyexamples.com/spark/sparksession-vs-sparkcontext/
* Spark With Scala chatroom : https://gitter.im/spark-scala/Lobby
* Матей Захария - Изучаем Spark : https://github.com/databricks/learning-spark

## Submit modes:
|Mode|Master|Supervize|Deploy Mode|Executor cores|
|-|-|-|-|-|
|Locally|--master local[*]||--client|-|
|Standalone cluster|--master spark://host:7077|--supervise|--cluster|--total-executor-cores 100
|YARN|--master yarn ||--cluster|--total-executor-cores 100
|Mesos|--master mesos://host:7077|--supervise|--cluster|--total-executor-cores 100
|Kubernetes|--master k8s://xx.yy.zz.ww:443 ||--cluster|--total-executor-cores 100

## Create SBT project

(Scala Hello-World)
```
sbt new scala/hello-world.g8
```

## Network ports

|Port|Desc|
|----|----|
|4040|WebUI for SparkShell|
|8080|WebUI|
|7077|Master URL|
|35307|Worker|
|33949|Driver Port|

```bash
$ watch -n 5 "netstat -plnt | grep -E '(4040|8080|7077|35307|33949)'"
```

## JPS
|Process|Desc|
|-------|----|
|org.apache.spark.deploy.SparkSubmit|Client|

## Monitoring and Logging

In addition, detailed log output for each job is also written to the work directory of each worker node (SPARK_HOME/work by default). You will see two files for each job, stdout and stderr, with all output it wrote to its console.


## Configure Environment

```
export SPARK_LOCAL_IP=127.0.0.1
```


## build.sbt (core + sql + mllib)

```
scalaVersion := "2.12.15" // For current version of Spark 3.2.1

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
  "org.apache.spark" %% "spark-core" % "3.2.1",
  "org.apache.spark" %% "spark-sql" % "3.2.1",
  "org.apache.spark" %% "spark-mllib" % "3.2.1"
)
```

## Spark-shell

```
spark-shell
```

## Submit
```
spark-submit \
 --master local[6] \
 --deploy-mode client \
 --class demo.Main \
 --name probe-context \
 target/scala-2.13/demo_2.13-0.1.jar
```

with custom configs
```
spark-submit \
  --name "My app" \
  --master local[4] \  
  --conf spark.eventLog.enabled=false \
  --conf "spark.executor.extraJavaOptions=-XX:+PrintGCDetails -XX:+PrintGCTimeStamps" \
  --conf spark.hadoop.abc.def=xyz \
  --conf spark.hive.abc=xyz
  myApp.jar
```


## Logging
```
$ cp log4j.properties.template log4j.properties

edit...
```

## Performance

Consider:

* spark.serializer
  ```
    .registerKryoClasses(Array(classOf[GeoIpCity]))
    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .set("spark.kryo.registrationRequired", "true")
  ```
* partitions
  ```

  ```

## Spark shell

### Enter to 'paste mode'

```
:paste
....
Ctrl+D
```

### Enter code-block
```
{
  ....
}
```

### SparkContext (from Spark 1.x)
```
scala> :t sc
org.apache.spark.SparkContext
```

### SparkSession (from Spark 2.x)
```
scala> :t spark
org.apache.spark.sql.SparkSession
```

## Entry point

```
object Main {

  def main(args : Array[String]): Unit = {

    val log : Logger = LoggerFactory.getLogger("app")
    val sparkSession = SparkSession
      .builder()
      .appName("my-app")
      .master("local[12]")
      .getOrCreate()
  }
```

## RDD/DataFrame/Dataset

|    |RDD|DataFrame|Dataset|Comments|
|----|---|---------|-------|--------|
|What|Collection|Named columns|Extension of DataFrame|
|When|1.0|1.3|1.6
|Compiletime type safety|No|No|Yes|
|API|No|Yes|Yes|Means: agg,select,sum|
|SparkSQL|No|Yes|Yes|
|Catalyst optimizer|No|Yes|Yes|
|Tungsten component|No|Yes|Yes|
|Advanced encoders|No|No|Yes|

## Syntax and analysis Errors handling

|      |SQL     |DataFrame  |Dataset    |
|------|--------|-----------|-----------|
|Syntax|Run time|Compiletime|Compiletime|
|Analysis|Run time|Run time|Compiletime|


## Typical RDD processing

```
val rddl = rdd.map(word => (word, l))
val resultRDD = rddl.reduceByKey(a,b => a + b)
resultRDD.collect.foreach(println)
```

## Same with DF

```
val dfl = df.groupBy("Value").count()
dfl.show()
```

## Tungsten component

* Avoids GC
* Occupies Less space
* Avoids expensive java serialization


The spark dataset does not use standard serializers(Kryo or Java serialization). Instead, it uses Tungsten’s fast in-memory encoders, which understand the internal structure of the data and can efficiently transform objects into internal binary storage. It uses off-heap data serialization using a Tungsten encoder, and hence there is no need for garbage collection.


## Spark + Deequu

**Keywords:SPARK,DEEQU,ORC,RDD**

## General Links

**Spark** (org.apache.spark) https://spark.apache.org/

Apache Spark is a fast and general-purpose cluster computing system.
It provides high-level APIs in Java, Scala, Python and R, and an
optimized engine that supports general execution graphs.
It also supports a rich set of higher-level tools including
Spark SQL for SQL and structured data processing, MLlib
for machine learning, GraphX for graph processing, and Spark Streaming.

- Spark SQL http://spark.apache.org/docs/latest/sql-programming-guide.html
- MLIB http://spark.apache.org/docs/latest/ml-guide.html
- GraphX http://spark.apache.org/docs/latest/graphx-programming-guide.html
- Spark Streaming http://spark.apache.org/docs/latest/streaming-programming-guide.html

**ORC** (org.apache.orc) https://orc.apache.org/  

**Spark + ORC** https://spark.apache.org/docs/latest/sql-data-sources-orc.html

**Deequu** https://aws.amazon.com/blogs/big-data/test-data-quality-at-scale-with-deequ/

**Hadoop** https://hadoop.apache.org/

**YARN** https://yarnpkg.com/

**Ganglia** (monitoring system) http://ganglia.info/

**Zeppelin** (interactive bigdata anlyze (like Athena?)) https://zeppelin.apache.org/

## Sources

- https://github.com/awslabs/deequ    
- https://github.com/apache/spark
- https://github.com/apache/orc

# Types

- org.apache.spark.sql.DataFrame
- org.apache.spark.sql.SparkSession
- org.apache.spark.sql.Row
- org.apache.spark.sql.Dataset
- org.apache.spark.sql.DataFrameWriter
- org.apache.hadoop.hive.ql.io.orc.OrcInputFormat

## Spark-shell

Display type:
```
scala> :type -v sc
// Type signature
org.apache.spark.SparkContext

// Internal Type structure
TypeRef(TypeSymbol(class SparkContext extends Logging))
```

## Spark Session

Already defined since 2.0
```
scala> :type spark
org.apache.spark.sql.SparkSession

scala> :type spark.sparkContext
org.apache.spark.SparkContext

scala> :type spark.sqlContext
org.apache.spark.sql.SQLContext
```

## Hive support
```
val spark = SparkSession
      .builder()
      .appName("fisher-iris")
      .master("local[12]")
      .enableHiveSupport()
      .config("spark.sql.warehouse.dir", "/bigdata/spark/warehouse")
      .withExtensions(extensions => {})
      .getOrCreate()
```

## Web UI

* http://localhost:8080/

## Create a Cluster With Spark

https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-launch.html

```
aws emr create-cluster --name "Spark cluster" --release-label emr-5.29.0 --applications Name=Spark \
 --ec2-attributes KeyName=myKey --instance-type m5.xlarge --instance-count 3 --use-default-roles
```

### Other options:

Spark: Spark 2.4.4 on Hadoop 2.8.5 Yarn with Ganglia 3.7.2 and Zeppelin 0.8.2

## Configure Spark

https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-configure.html

### Instances Available

|Instance |
|---------|
|m5.xlarge|

## Amazon EMR console

https://console.aws.amazon.com/elasticmapreduce/

## Spark CLI
Must be Java-8
```
$ java -version
java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b10)
Java HotSpot(TM) 64-Bit Server VM (build 25.212-b10, mixed mode)
```

## Shell (+4g)

Retieve RDD from text file
```
$ ./spark-shell --master "local[4]" --driver-memory 4G

scala> val inputFile = sc.textFile("/bigdata/GeoIPCity.utf-8.csv")
inputFile: org.apache.spark.rdd.RDD[String] = /bigdata/GeoIPCity.utf-8.csv MapPartitionsRDD[1] at textFile at <console>:24
```

Count words:
```
scala> val counts = inputFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
counts: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[4] at reduceByKey at <console>:25
```

Debug execution plan
```
scala> counts.toDebugString
res1: String =
(13) ShuffledRDD[4] at reduceByKey at <console>:25 []
 +-(13) MapPartitionsRDD[3] at map at <console>:25 []
    |   MapPartitionsRDD[2] at flatMap at <console>:25 []
    |   /bigdata/GeoIPCity.utf-8.csv MapPartitionsRDD[1] at textFile at <console>:24 []
    |   /bigdata/GeoIPCity.utf-8.csv HadoopRDD[0] at textFile at <console>:24 []
```

Save Result
```
counts.saveAsTextFile("/bigdata/res1-text")

20/03/14 23:06:57 WARN BlockManager: Block rdd_4_11 could not be removed as it was not found on disk or in memory
20/03/14 23:06:57 WARN BlockManager: Putting block rdd_4_11 failed
```

Fix (+4g):
```
$ ./spark-shell --master "local[4]" --driver-memory 4G
```
OK

Trying to save as binary
```
scala> counts.saveAsObjectFile("/bigdata/res1-obj")
java.lang.IllegalArgumentException: Unsupported class file major version 55
  at org.apache.xbean.asm6.ClassReader.<init>(ClassReader.java:166)
```

Repartition (create new variable due to immutability)
```
val repartitioned = counts.repartition(2)
```

Persist disk only
```
counts.persist(org.apache.spark.storage.StorageLevel.DISK_ONLY)
```

Unpersist from memory
```
scala> counts.unpersist()
res6: counts.type = ShuffledRDD[4] at reduceByKey at <console>:25
```

### From RDD to DataFrame

(maybe this is not the single case?)
```
val irisDf : DataFrame = spark
      .read
      .option("header", false)
      .option("delimiter", ",")
      .csv("hdfs://..../iris.data")
      .withColumnRenamed("_c0", "sepal_length")
      .withColumnRenamed("_c1", "sepal_width")
      .withColumnRenamed("_c2", "petal_length")
      .withColumnRenamed("_c3", "petal_width")
      .withColumnRenamed("_c4", "clazz")

val irisDs1 = irisDf.select(
    col("sepal_length").cast("Double"),
    col("sepal_width").cast("Double"),
    col("petal_length").cast("Double"),
    col("petal_width").cast("Double"),
    col("clazz"))
```

### Storage levels:
|Level|
|-----|
|MEMORY_ONLY|
|MEMORY_ONLY_SER|
|MEMORY_AND_DISK|
|MEMORY_AND_DISK_SER|
|DISK_ONLY|

### File Formats

|Format|Structured|Desc|
|------|----------|----|
|Text|no|
|JSON|semi|
|CSV|yes|
|Sequence|yes|Hadoop key-valye|
|Protocol Buffers|yes|
|Object files|yes|High speed|

### Skip Corrupt files

spark.sql.files.ignoreCorruptFiles = true

### JSON: Dealing with corrupt records

```
spark.read
.option("mode","PERMISSIVE")
.option("columnNameOfCorruptRec","_corrupt")
.json("corrupt.json")
.show()
```

### Consider Hadoop formats:

|Format|Structured|Splittable|Schema evolution|Desc|
|------|----------|----------|----------------|----|
|AVRO  |          |yes       |yes, was mainly designed for Schema evolution. Fields can renamed, added, deleted while old files can still be read with the new schema|Uses JSON for defining, but binary for store |

### Concepts

https://data-flair.training/blogs/apache-spark-rdd-vs-dataframe-vs-dataset/

|Class    |Data Representation|
|---------|-----|
|RDD|RDD is a distributed collection of data elements spread across many machines in the cluster. RDDs are a set of Java or Scala objects representing data.|
|DataFrame|A DataFrame is a distributed collection of data organized into named columns. It is conceptually equal to a table in a relational database.|
|DataSet|It is an extension of DataFrame API that provides the functionality of – type-safe, object-oriented programming interface of the RDD API and performance benefits of the Catalyst query optimizer and off heap storage mechanism of a DataFrame API.|

|Class    |Schema|Data Sources|
|---------|------|------------|
|RDD|-|Text files|
|DataFrame|+|Avro,CSV,JSON,HDFS,Hivetables|
|DataSet|?|?|

## Dictionary

# RDD

- Resillent Distributed Datasets

# DataFrame

-

# DAG

- Direct Acyclic Graph

# Transformations
(map, flatMap, filter, distinct)
  - with couple arguments (union, intersect, subtract, cathesian)

# Actions
reduce, fold, aggregate

# Numeric transforma
- mean
- variance


## Examples

Parse text file
```
val df = spark.read().format("csv").option("delimiter", " ").option("header", "false").load(dir_path)
ds.filter("user='shit'").show();
```


```
val dataset = spark.read.parquet("s3://amazon-reviews-pds/parquet/product_category=Electronics/")

import com.amazon.deequ.analyzers.{Compliance, Correlation, Size, Completeness, Mean, ApproxCountDistinct}

val analysisResult: AnalyzerContext = { AnalysisRunner
  // data to run the analysis on
  .onData(dataset)
  // define analyzers that compute metrics
  .addAnalyzer(Size())
  .addAnalyzer(Completeness("review_id"))
  .addAnalyzer(ApproxCountDistinct("review_id"))
  .addAnalyzer(Mean("star_rating"))
  .addAnalyzer(Compliance("top star_rating", "star_rating >= 4.0"))
  .addAnalyzer(Correlation("total_votes", "star_rating"))
  .addAnalyzer(Correlation("total_votes", "helpful_votes"))
  // compute metrics
  .run()
}

// retrieve successfully computed metrics as a Spark data frame
val metrics = successMetricsAsDataFrame(spark, analysisResult)
```

```
import com.amazon.deequ.{VerificationSuite, VerificationResult}
import com.amazon.deequ.VerificationResult.checkResultsAsDataFrame
import com.amazon.deequ.checks.{Check, CheckLevel}

val verificationResult: VerificationResult = { VerificationSuite()
  // data to run the verification on
  .onData(dataset)
  // define a data quality check
  .addCheck(
    Check(CheckLevel.Error, "Review Check")
      .hasSize(_ >= 3000000) // at least 3 million rows
      .hasMin("star_rating", _ == 1.0) // min is 1.0
      .hasMax("star_rating", _ == 5.0) // max is 5.0
      .isComplete("review_id") // should never be NULL
      .isUnique("review_id") // should not contain duplicates
      .isComplete("marketplace") // should never be NULL
      // contains only the listed values
      .isContainedIn("marketplace", Array("US", "UK", "DE", "JP", "FR"))
      .isNonNegative("year")) // should not contain negative values
```



## Serialization

```scala
case class BusinessEntity(Id : Long) extends Serializable with Product {}
```

## Standalone cluster

```bash
# Configure Java8/11
./start-master.sh -h localhost
./start-worker.sh localhost:7077
```

submit:
```bash
spark-submit \
 --master spark://localhost:7077 \
 --deploy-mode cluster \
 --supervise \
 --executor-memory 1G \
 --total-executor-cores 12 \
 --verbose \
 --name Name \
 --driver-memory 4G \
 --class "com.Main" \
 target/scala-2.12/Main_2.12-1.0.jar
```

## WHERE IS NOT NULL

Case 1
```
df.filter(col("email").isNotNull && col("hash").isNotNull)
```
Case 2
```
df.where("email is not null and hash is not null")
```

## Collaspse partition into single (to before generate CSV report)

```
result
  .coalesce(1)
  .write
  .csv("/report/report.csv")
```

## Off heap

```
spark.memory.offHeap.enabled=true
spark.memory.offHeap.size=512m
```

## CSV schema infer

```
val geoipData = spark.read.option("inferSchema",true).option("header",true).option("delimiter",",").csv("/...../GeoIPCity.utf-8.csv.bz2")
geoipData: org.apache.spark.sql.DataFrame = [startIpNum: string, endIpNum: string ... 8 more fields]

scala> geoipData.printSchema
root
 |-- startIpNum: string (nullable = true)
 |-- endIpNum: string (nullable = true)
 |-- country: string (nullable = true)
 |-- region: string (nullable = true)
 |-- city: string (nullable = true)
 |-- postalCode: string (nullable = true)
 |-- latitude: double (nullable = true)
 |-- longitude: double (nullable = true)
 |-- dmaCode: integer (nullable = true)
 |-- areaCode: integer (nullable = true)

```
