# FAQ

## Compare SQL and Dataframe API:
```sql
SELECT id, result
FROM exams
WHERE result > 70
ORDER BY result
```

```scala
spark.table("emp")
 .select("id","ename")
 .where("sal > 6.0")
 .orderBy("result")
```

## How to call another notebook
```
%run ./Includes/Classroom-Setup
```
## Execute R language
```
%r
print("Run R", quote=FALSE)
```
## Run shell
```
%sh ps | grep 'java'
```
## Render html
```
html = """<h1 style="....">Hello</h1>"""
displayHTML(html)
```


## Q : Stage X exceeded maximum allowed ratio of input to output records

A:
```
set spark.databricks.queryWatchdog.outputRatioThreshold=50000
```

## Q : Unable to review DBFS filesystem

* Go to admin settings...

## Q : How to transform from unixtime to time?

Assumed that unixtime is decumal int32 number.

```scala
df.SELECT(
  from_unixtime("field1").cast("date").alias("vdate"),
  from_unixtime("field1").cast("timestamp").alias("vtime"),
  ....
)
```

## Q : How to collapse all partition into single for best report look?
```scala
df.collapse(...)
```

## Q : How to force OVERWRITE report file?
```scala
.mode("Overwrite")
```
## Q: When deltalog will be applied into tables?

https://databricks.com/blog/2019/08/21/diving-into-delta-lake-unpacking-the-transaction-log.html

Once we’ve made a total of 10 commits to the transaction log, Delta Lake saves a checkpoint file in Parquet format in the same _delta_log subdirectory. Delta Lake automatically generates checkpoint files every 10 commits.

## Q: How to know current user
```
SELECT current_user();
```
