# Databricks Datatypes (Delta)

## Detect type from literal

```sql
select typeof(1);
int
select typeof();
```

## Complex types are composed of multiple components of complex or simple types:

* https://docs.databricks.com/sql/language-manual/sql-ref-datatypes.html



|Data Type|Description|
|-|-|
|ARRAY<elementType>|Represents values comprising a sequence of elements with the type of elementType.|
|MAP<keyType,valueType>|Represents values comprising a set of key-value pairs.|
|STRUCT<[fieldName:fieldType [NOT NULL][COMMENT str][, …]]>|Represents values with the structure described by a sequence of fields.|

## Samples:

array
```sql
> SELECT ARRAY(1, 2, 3);
  [1, 2, 3]

> SELECT CAST(ARRAY(1, 2, 3) AS ARRAY<TINYINT>);
  [1, 2, 3]

create table DEPTS_COLLECTION(
  id int,
  employees ARRAY<string>
) using parquet;

insert into DEPTS_COLLECTION values(10, ARRAY('CLARK','KING','MILLER'));
insert into DEPTS_COLLECTION values(20, ARRAY('SMITH','JONES','SCOTT'));
```

map
```sql
> SELECT map('red', 1, 'green', 2);
  {red->1, green->2}

> SELECT typeof(CAST(NULL AS MAP<TIMESTAMP, INT>));
  MAP<TIMESTAMP, INT>

> SELECT map(array(1, 2), map('green', 5));
  {[1, 2]->{green->5}}

> SELECT CAST(map(struct('Hello', 'World'), 'Greeting') AS MAP<STRUCT<w1:string, w2:string>, string>);
  {{Hello, World}->Greeting}
```

struct
```
create table emp2(s struct<EMPNO:int not null,ENAME:string not null,JOB:string,MGR:int,HIREDATE:date,SAL:double,COMM:double,DEPTNO:int>) using parquet;
```

## Aggregate rows into cells:

```

```
