# Partitioned tables

```scala
// Create managed table as select
dataframe.write.mode(SaveMode.Overwrite).partitionBy("id").saveAsTable("<example-table>")

// Create unmanaged/external table as select
dataframe.write.mode(SaveMode.Overwrite).option("path", "<file-path>").saveAsTable("<example-table>")
```

## Partitioned
```
create table emp(
  empno integer,
  ename string,
  job string,
  mgr integer,
  hiredate date,
  sal decimal,
  comm decimal,
  depno integer)
using delta
partitioned by depno;
```

## Repartition
```

```
