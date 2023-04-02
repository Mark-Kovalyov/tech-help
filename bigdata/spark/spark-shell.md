# Spark-shell

## Run spark-shell with direct memory for Executor and Driver

```
spark-shell --driver-memory 2G --executor-memory 3G --executor-cores 4
```

## Dialects

## Aproach 1 : Spark-shell
```
$ spark-shell

or

$ spark-shell --master yarn --conf "spark.ui.port=7777"

scala> val sql = spark.sqlContext
sql: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@6451fd0e

```
### Create table with AVRO provider
```
$ spark-shell --packages org.apache.spark:spark-avro_2.12:3.2.1
scala> spark.sqlContext.sql("create table test3(id int) using avro")
res0: org.apache.spark.sql.DataFrame = []
```
## Disk segment (by defalt == /home/$USER):

### spark-warehouse
```
~/spark-warehouse/
   +- tablename
```

### metastore_db
```   
~/metastore_db/
   + log/
   + seg0/
   + tmp/
   + dbex.lck
   + db.lck   
   + README_DO_NOT_TOUCH_FILES.txt   
   + service.properties   
```

### Peek disk segment
```
export SPARK_LOCAL_DIRS=...
```
```



$ spark-shell --conf "spark.local.dir=/bigdata/spark/spark.local.dir" --conf "spark.sql.warehouse.dir=/bigdata/spark/spark.sql.warehouse.dir" --conf "hive.metastore.warehouse.dir=/bigdata/spark/hive.metastore.warehouse.dir"
```

## Aproach 2 : Spark-sql (peek local.dir + header on)

```
spark-sql --conf "spark.hadoop.hive.cli.print.header=true"
spark-sql --conf "spark.local.dir=/bigdata/spark/spark.local.dir" --conf "spark.hadoop.hive.cli.print.header=true"
```

## Create table
```
spark-sql> create table test(id int) using parquet;
```
physical location:
```
```

### Using hive data_source

### Using hive format

## Links

* https://www.youtube.com/watch?v=E-bf_lZqROA
