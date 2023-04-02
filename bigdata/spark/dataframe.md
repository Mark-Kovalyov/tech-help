# Dataframes

## Generate Df from RDD+Schema

```
val schema : StructType = StructType(
   Seq(StructField("name", StringType, false),
       StructField("age", IntegerType, false))
)
val rdd : RDD[Row] = spark.sparkContext.parallelize(Seq(Row("huawei",34),Row("Bob",19)))
var dataFrame : DataFrame = spark.createDataFrame(rdd, schema)
```
