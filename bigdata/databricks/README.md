# Databriks

## Links

* Youtube : https://www.youtube.com/c/Databricks
* News : https://databricks.com/newsroom
* Spark packages : https://spark-packages.org/

|Cluster Runtime|Software               |Delta Lake|
|---------------|-----------------------|----------|
|11.2           |Spark 3.3.0            |2.1.0
|11.1           |Spark 3.3.0            |2.0.0
|11.0           |Spark 3.3.0            |1.2.1
|10.5           |Scala 2.12, Spark 3.2.1|1.2.1
|10.5 ML        |Scala 2.12, Spark 3.2.1|1.2.1
|10.5 LTS       |Scala 2.12, Spark 3.2.1|1.2.1
|10.5 LTS ML    |Scala 2.12, Spark 3.2.1|1.2.1
|9.1 LTS        |Spark 3.1.2            |1.0.0

## Install custom package to Cluster:
```

```

## Tokens
```
export DATABRICKS_AAD_TOKEN=<Azure-AD-token>

or

export DATABRICKS_AAD_TOKEN=$(az account get-access-token | jq .accessToken --raw-output)
```


## File structure

### Create table using DELTA format (Parquet?)

```
CREATE TABLE test(id int) USING DELTA;
```

The file structure will be:
```
/user/hive/warehouse/test/_delta_log
  /__tmp_path_dir
  00000000.crc
  00000000.json
```



### How to browse fs:
```python
%python
dbutils.fs.ls("/")
```

### Peek Delta location during creation
```
CREATE TABLE IF NOT EXISTS mytab USING DELTA LOCATION '/mnt/delta/mytab'
```

### Show Delta transaction log (trick :))

```python
%python
import os, pprint
path = "/dbfs/user/...../000000.json"
with open(path,"r") as file
  text = file.read()
pprint.pprint(text)b
```

### CSV
```
CREATE TABLE test2(id int) USING CSV;
```

## Partitioned

```
CREATE TABLE students (name varchar(64), addr varchar(64), id INT) USING DELTA
PARTITIONED BY (id);
```
## Overwrite option during insert
```
INSERT OVERWRITE students VALUES
      (...)
      (...)
```
## Merge
```
MERGE INTO tab1
USING tab2
ON tab1.id = tab2.id
WHEN MATCHED THEN DELETE;
```

## Retrospective queries
(version=v1)
```
SELECT * FROM students@v1
```
the same as
```
SELECT * FROM students VERSION AS OF 1
```

## Vacuum
```
VACUUM tab1 RETAIN 24 HOURS DRY RUN
```
'dry run' - will return a list of files to be deleted

another aproach:

```python

try:
  deltaTab.vacuum()
except IllegalArgumentException as err:
  print(rtt)  
```

yet another sample:
```
VACUUM delta.`dbfs:/mnt/......./table1` RETAIN 24 HOUR;
```

## Optimization
```
OPTIMIZE tab1;

CREATE BLOOMFILTER INDEX
ON TABLE tab1
FOR COLUMNS(name OPTIONS(fpp=0.01, numItems=100000))
```
See: https://docs.databricks.com/spark/latest/spark-sql/language-manual/delta-create-bloomfilter-index.html


### Bloom filter

## Managed/Unmanaged

| |Managed|Unmanaged|
|-|-|-|
||||

## Clone and Backup

### Clones
* Replica of target table
* At point in time
* Peek dest
* Clones have separate lineage.
  * Changes to cloned table doesn't affect source
  * Changes in source during cloning are not reflected in the clone

### Shallow clones

* Only metadata
* Points to original file
* Fast
* Depends on source files

### Deep

* Data + metadata copied
* Copy is optimized, transactional and robust
* Incrementally copies data files

## Incremenal cloning

* Only new written data files are copied
* Updates, deletes are automatically applied
* Data files will be identical in both tables

## Versioning

* Clones have separate Versioning
  * History from 0  
* Clones can have separate retention   

## Vizualize

```
display(df)
```

## Make idempotent for some Actions
```
dbutils.fs.rm("/....../tab", recurse=True)
spark_sql(f"DROP TABLE IF EXISTS tab")
```

## Schema evolution

Append mode:
```python
  df.write.mode("append").save(".....")
```

For DataFrame reader/writer:
```
.option("mergeSchema", true)
```
For others:
```
spark.Databriks.delta.schema.autoMerge.enabled
```

## Deleting records (set null/None)
```
upsertDF = (
  spark.read.options("versionAsOf", 11)
  .format("delta")
  .load(..)
  .where(...)
  .select("f1","f2",lit(None).alias("personName"))
  )
```

## The most reason properties for Scala-notebook

```scala
val envMap : java.util.Map[String,String] = System.getenv()

// SCALA_VERSION = 2.10
// SPARK_SCALA_VERSION = 2.12
// DATABRICKS_RUNTIME_VERSION = 9.1
```

## System properties
```
val prop : Properties = System.getProperties()

// java.runtime.version = 1.8....
// spark.executor.memory = 7284m
```
