# Databricks Table size

## With explain detail
```
import org.apache.spark.sql.{Dataset, DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions.col

def tableStats(schema:String, list: Seq[String]): Seq[DatabricksTableStat] = {
  for (table <- list) yield {
    val r: Array[Row] = spark
        .sql(s"describe detail ${schema}.${table}")
        .select("sizeInBytes","format","name","location")
        .filter($"sizeInBytes".isNotNull)
        .filter($"format".isNotNull)
        .filter($"name".isNotNull)
        .filter($"location".isNotNull)
        .collect()
    if (r.length==1) {
      val rowzero: Row = r(0)
      val size: Long = rowzero.getLong(0)
      val format     = rowzero.getString(1)
      val full_name  = rowzero.getString(2)
      val location   = rowzero.getString(3)
      DatabricksTableStat(full_name, size, format, location, "")      
    } else {
      DatabricksTableStat(s"${schema}.${table}", 0L, "",  "", "ERROR:No details about " + table)
    }  
  }
}
```

## With API (What about log size?)

```
%scala

import com.databricks.sql.transaction.tahoe._

val deltaLog = DeltaLog.forTable(spark, s"dbfs:/${path-to-delta-table}")
val snapshot = deltaLog.snapshot              
println(s"Total file size (bytes): ${deltaLog.snapshot.sizeInBytes}")

```

## With statistics (non delta table)
```
%scala

spark.read.table(a"${non-delta-table-name}").queryExecution.analyzed.stats
```

