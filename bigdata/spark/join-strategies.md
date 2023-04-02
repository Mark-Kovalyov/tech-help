# Spark Join Strategies

https://towardsdatascience.com/strategies-of-spark-join-c0e7b4572bcf

Kind of joins
* inner
* outer 
* left or (maybe) left-semi

Strategies
* Broadcast Hash Join
  * spark.sql.autoBroadcastJoinThreshold = 10m
* Shuffle hash join
* Shuffle sort-merge join
* Cartesian Join
* Broadcast nested loop join ('=','<=','<',e.t.c)
