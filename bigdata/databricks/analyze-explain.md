# Analyze table and Explain execution plan

## Links

* Spark’s Logical and Physical plans … When, Why, How and Beyond. https://medium.com/datalex/sparks-logical-and-physical-plans-when-why-how-and-beyond-8cd1947b605a
* Shalini Goutam - Apache Spark Logical And Physical Plans https://blog.clairvoyantsoft.com/spark-logical-and-physical-plans-469a0c061d9e
* Mastering Query Plans in Spark 3.0 - https://towardsdatascience.com/mastering-query-plans-in-spark-3-0-f4c334663aa4
* Apache Spark - Spark Internals | Spark Execution Plan With Example | Spark Tutorial https://www.youtube.com/watch?v=dCvxE2WSOsE

## Analyze

```sql
ANALYZE TABLE <tab> COMPUTE STATISTICS;
or
ANALYZE TABLES <schemaname> COMPUTE STATISTICS;
```

## Explain

Syntax:
```
EXPLAIN [ EXTENDED | CODEGEN | COST | FORMATTED ] <statement>;
```

### Samples
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
using parquet
partitioned by (depno);
analyze table emp compute statistics;
....
```

simple
```
spark-sql> explain select * from emp where depno = 10;
plan
== Physical Plan ==
*(1) ColumnarToRow
+- FileScan parquet default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] Batched: true, DataFilters: [], Format: Parquet, Location: InMemoryFileIndex(1 paths)[file:/bigdata/spark/spark-sql/spark-warehouse/emp/depno=10], PartitionFilters: [isnotnull(depno#637), (depno#637 = 10)], PushedFilters: [], ReadSchema: struct<empno:int,ename:string,job:string,mgr:int,hiredate:date,sal:decimal(10,0),comm:decimal(10,0)>
```

cost
```
spark-sql> explain COST select * from emp where depno = 10;
plan
== Optimized Logical Plan ==
Filter (isnotnull(depno#637) AND (depno#637 = 10)), Statistics(sizeInBytes=5.4 KiB)
+- Relation default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] parquet, Statistics(sizeInBytes=5.4 KiB)

== Physical Plan ==
*(1) ColumnarToRow
+- FileScan parquet default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] Batched: true, DataFilters: [], Format: Parquet, Location: InMemoryFileIndex(1 paths)[file:/bigdata/spark/spark-sql/spark-warehouse/emp/depno=10], PartitionFilters: [isnotnull(depno#637), (depno#637 = 10)], PushedFilters: [], ReadSchema: struct<empno:int,ename:string,job:string,mgr:int,hiredate:date,sal:decimal(10,0),comm:decimal(10,0)>
```

extended
```
spark-sql> explain EXTENDED select * from emp where depno = 10;
plan

== Parsed Logical Plan ==
'Project [*]
+- 'Filter ('depno = 10)
   +- 'UnresolvedRelation [emp], [], false

== Analyzed Logical Plan ==
empno: int, ename: string, job: string, mgr: int, hiredate: date, sal: decimal(10,0), comm: decimal(10,0), depno: int
Project [empno#630, ename#631, job#632, mgr#633, hiredate#634, sal#635, comm#636, depno#637]
+- Filter (depno#637 = 10)
   +- SubqueryAlias spark_catalog.default.emp
      +- Relation default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] parquet

== Optimized Logical Plan ==
Filter (isnotnull(depno#637) AND (depno#637 = 10))
+- Relation default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] parquet

== Physical Plan ==
*(1) ColumnarToRow
+- FileScan parquet default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] Batched: true, DataFilters: [], Format: Parquet, Location: InMemoryFileIndex(1 paths)[file:/bigdata/spark/spark-sql/spark-warehouse/emp/depno=10], PartitionFilters: [isnotnull(depno#637), (depno#637 = 10)], PushedFilters: [], ReadSchema: struct<empno:int,ename:string,job:string,mgr:int,hiredate:date,sal:decimal(10,0),comm:decimal(10,0)>

Time taken: 0.055 seconds, Fetched 1 row(s)
```

codegen
```
spark-sql> explain CODEGEN select * from emp where depno = 10;
plan
Found 1 WholeStageCodegen subtrees.
== Subtree 1 / 1 (maxMethodCodeSize:667; maxConstantPoolSize:143(0.22% used); numInnerClasses:0) ==
*(1) ColumnarToRow
+- FileScan parquet default.emp[empno#630,ename#631,job#632,mgr#633,hiredate#634,sal#635,comm#636,depno#637] Batched: true, DataFilters: [], Format: Parquet, Location: InMemoryFileIndex(1 paths)[file:/bigdata/spark/spark-sql/spark-warehouse/emp/depno=10], PartitionFilters: [isnotnull(depno#637), (depno#637 = 10)], PushedFilters: [], ReadSchema: struct<empno:int,ename:string,job:string,mgr:int,hiredate:date,sal:decimal(10,0),comm:decimal(10,0)>

Generated code:
/* 001 */ public Object generate(Object[] references) {
/* 002 */   return new GeneratedIteratorForCodegenStage1(references);
/* 003 */ }
/* 004 */
/* 005 */ // codegenStageId=1
/* 006 */ final class GeneratedIteratorForCodegenStage1 extends org.apache.spark.sql.execution.BufferedRowIterator {
/* 007 */   private Object[] references;
/* 008 */   private scala.collection.Iterator[] inputs;
/* 009 */   private int columnartorow_batchIdx_0;
/* 010 */   private org.apache.spark.sql.execution.vectorized.OnHeapColumnVector[] columnartorow_mutableStateArray_2 = new org.apache.spark.sql.execution.vectorized.OnHeapColumnVector[8];
/* 011 */   private org.apache.spark.sql.catalyst.expressions.codegen.UnsafeRowWriter[] columnartorow_mutableStateArray_3 = new org.apache.spark.sql.catalyst.expressions.codegen.UnsafeRowWriter[1];
/* 012 */   private org.apache.spark.sql.vectorized.ColumnarBatch[] columnartorow_mutableStateArray_1 = new org.apache.spark.sql.vectorized.ColumnarBatch[1];
/* 013 */   private scala.collection.Iterator[] columnartorow_mutableStateArray_0 = new scala.collection.Iterator[1];
..................
```
