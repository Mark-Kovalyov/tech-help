# Delta tables

## Types of tables
* Global - is available accross all clusters.
* Local is not accessible from other clusters and is not registered in the Hive
  metastore. This is also known as temporary view.

### Create table as select from csv
```
create table geoip1 using parquet as select * from csv.`/bigdata/GeoIPCity.utf-8.csv.bz2`;
```  

### Create Local Table
```python
dataFrame = "/databricks-datasets/Rdatasets/data-001/csv/ggplot2/diamonds.csv"
spark.read.format("csv").option("header","true")\
  .option("inferSchema", "true").load(dataFrame)\
  .createOrReplaceTempView("diamonds")
```  

### Update Schema

The table schema is immutable. However, you can update table data by changing the underlying files.

For example, for tables created from an S3 directory, adding or removing files in that directory changes the contents of the table.

After updating the files underlying a table, refresh the table using the following command:
```
REFRESH TABLE <table-name>
```

## Create database
```scala
spark.sql("DROP DATABASE IF EXISTS {}_db CASCADE".format(str(databricks_user)));
spark.sql("CREATE DATABASE IF NOT EXISTS {}_db".format(str(databricks_user)));
spark.sql("USE {}_db".format(str(databricks_user)));
```  

```sql
create database USER_NAME;
```
generates structure:
```sql
dbfs://user/hive/warehouse/USER_NAME.db/
```

```sql
use USER_NAME;
create table test2(id int) using delta;
insert into test2 values(1);
insert into test2 values(2);
```

## Using data_source
|Table type|aproach|
|-|-|
|AVRO|
|CSV|
|JSON|
|PARQUET|
|ORC|
|DELTA|Good for all|

## Create delta table from already existing filesystem (wasbs)

```scala
val df = spark
  .read
  .format("delta")
  .load("dbname.tablename")

display(df)  
```

## Describe table
```
DESCRIBE TABLE EXTENDED <tableName>;
```
## List files in filesystem (dbfs)
```
dbutils.fs.ls('/UserName')
```

## Drop files
```
dbutils.fs.rm('/UserName', recurse=True)
```

## Create Delta table (managed)
```
CREATE TABLE <example-table>(id STRING, value STRING)
```
## Create Unmanaged (external) Delta table
```sql
CREATE TABLE <example-table>(id STRING, value STRING) USING org.apache.spark.sql.parquet OPTIONS (PATH "<your-storage-path>")
```

Difference

|Option    |Managed|Unmanaged|
|----------|-------|---------|
|DROP TABLE|Removes all|Removes only metadata|

## Remove Unmanaged
```
DROP TABLE IF EXISTS <example-table>    // deletes the metadata
dbutils.fs.rm("<your-s3-path>", true)   // deletes the data
```

## Recommended way to replace table contents

To avoid potential consistency issues, the best approach to replacing table contents is to overwrite the table.
```
CREATE TABLE <example-table>(id STRING, value STRING) USING org.apache.spark.sql.parquet OPTIONS (PATH "<your-s3-path>")
INSERT OVERWRITE TABLE <example-table> SELECT ...
```


## Delta DBFS folder structure:

```
_delta_log/__tmp_path_dir
_delta_log/0000000000000.crc
_delta_log/0000000000000.json
_delta_log/0000000000001.crc
_delta_log/0000000000001.json
_delta_log/....
_delta_log/0000000000010.checkpoint.parquet
_delta_log/....
part-00000-9874590843756987.snappy.parquet
part-00001-4536u498u6394566.snappy.parquet
....
part-00008-5u498u6394564446.snappy.parquet
....
```

## Live Table

```sql
CREATE streaming LIVE TABLE raw_station TAB_PROPERTIES ("quality" = "bronze")
AS
SELECT * FROM cloud_files("/FileStore/..../station_info", "json",
  map("cloudFIles.inferColumnTypes", "true"));
```

## Calculate table Physical segment size ?
```scala
def dbfsPath(db : String, table : String) : String = s"{db} {}";
```

```scala
import com.databricks.sql.transaction.tahoe._
val deltaLog = DeltaLog.forTable(spark, "dbfs:/<path-to-delta-table>")
val snapshot = deltaLog.snapshot               // the current delta table snapshot
val bytesSize = deltaLog.snapshot.sizeInBytes
println(s"Total file size (bytes): ${bytesSize}")
```

## Select data from Delta table files:
```sql
%sql

```
