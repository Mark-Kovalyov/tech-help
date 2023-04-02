# Schema conversion

## From AVRO to schema (?)
```
[
  {
   "namespace":"oracle.education.emp",
   "type" : "record",
   "name" : "Emp",
   "doc"  : "Employee table",
   "fields" : [
     { "name" : "EMPNO",    "type" : "int" },
     { "name" : "ENAME",    "type" : "string" },
     { "name" : "JOB",      "type" : "string" },
     { "name" : "MGR",      "type" : ["null", "int"] },
     { "name" : "HIREDATE", "type" : ["null", "string"] },
     { "name" : "SAL",      "type" : ["null", "double"] },
     { "name" : "COMM",     "type" : ["null", "double"] },
     { "name" : "DEPTNO",   "type" : "int" }
   ]
  }
]
```


## From json to schema (StructType)
```scala
    val json =
      """
        |{
        |  "type" : "struct",
        |  "fields" : [
        |    {
        |      "name" : "empno",
        |      "type" : "string",
        |      "nullable" : true,
        |      "metadata" : {}
        |    }
        |  ]
        |}
        |""".stripMargin

    val empSchema : StructType = DataType.fromJson(json).asInstanceOf[StructType]
```

## From case class to schema

```scala
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.types.StructType

import java.time.LocalDate

case class Emp(
   empno: Int,
   ename: String,
   job: String,
   mgr: Option[Int],
   hiredate: LocalDate,
   sal: Double,
   comm: Option[Double],
   deptno: Int)

val empSchema : StructType = ScalaReflection.schemaFor[Emp].dataType.asInstanceOf[StructType]
empSchema.printTreeString()
```

```
scala> empSchema.printTreeString()
root
 |-- empno: integer (nullable = false)
 |-- ename: string (nullable = true)
 |-- job: string (nullable = true)
 |-- mgr: integer (nullable = true)
 |-- hiredate: date (nullable = true)
 |-- sal: double (nullable = false)
 |-- comm: double (nullable = true)
 |-- deptno: integer (nullable = false)
```

## Schema from DDL-string

```scala
val ddlSchema  : StructType = StructType.fromDDL("empno INT, ename STRING, job STRING, mgr INT, hiredate DATE, sal DOUBLE, comm DOUBLE, deptno INT")
ddlSchema.printTreeString()
```

```scala
val ddlSchema : StructType = StructType.fromDDL("`fullName` STRUCT<`first`: STRING, `last`: STRING, `middle`: STRING>,`age` INT,`gender` STRING")
ddlSchema.printTreeString()
```

## DDL from Schema
```scala
val df : DataFrame = ...
print(df.schema.toDDL)
```