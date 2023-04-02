# Parquet and Delta tables

## Git

* https://github.com/apache/parquet-format
* https://github.com/apache/parquet-mr
* https://github.com/apache/parquet-cpp
* https://github.com/apache/arrow-rs/tree/master/parquet

## Q : How to create parquet table?

```python
spark.sql(
  f"CREATE TABLE tab1 USING parquet LOCATION "/...../tab1"
)
```

## Q : How to register the partitions?

If you create partitioned table from existing data,
Spark SQL does not automatically discover the partitions
and register them in the Metastore.

```
spark.sql("MSCK REPAIR TABLE tab1")
```

## Q : Convert parquet-based table to Delta?

Convert files to delta files:
```
DESCRIBE EXTENDED tab1;
```

```python
from delta.tables import DeltaTable
DataTable.convertToDelta(spark, "parquet_tab", partitioning_scheme)
```
Register delta table
```
spark.sql(f"DROP TABLE IF EXISTS tab1")
spark.sql(f"CREATE TABLE tab1 USING DELTA LOCATION "/...../tab1")
```
Add comments if needed
```SQL
ALTER TABLE
 tab1
REPLACE COLUMNS
 (dt DATE COMMENT "The format is : MM-DD-YYYY")
```
Review
```
DESCRIBE EXTENDED tab1;
```

## Some interesting renaming filed practices:
```python
df
 .withColumn("time", from_unixtime("time"))
 .withColumnRenamed("id", "subtype")
 ....
```

## Versioning, time travel
```
spark
  .read
  .option("versionAsOf", 0)
  .format("delta")....
```

## SSOT - Single Source of Truth
```
```
