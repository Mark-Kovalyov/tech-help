# Some interesting warnings received from Databricks

## Shuffle parition number too small:

We recommended enabling Auto-Optimized Shuffle by setting
```
spark.sql.shuffle.partitions=auto
```
or changins
```
spark.sql.shuffle.partitions
```
to 6000 or higher
