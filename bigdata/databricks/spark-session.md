# Spark Sesssion

Methods:
* sql 
* table 
* read
* range
* createDataFrame

# DataFrame action methods:

* show
* count
* desc
* first, head
* collect
* take

# SQL => DataFrame =>SQL
```
emp.createOrReplaceTempView("emp")

spark.sql("select * from emp")
```