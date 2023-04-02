# Transfer data between Environments

## Given

* Source: "wasbs://data@xxxx.blob.core.windows.net/warehouse/mydb.DB/mytable"

Step1
```scala
dbutils.fs.mount(
  source = "wasbs://<container-name>@<storage-account-name>.blob.core.windows.net/<directory-name>",
  mountPoint = "/mnt/<mount-name>",
  extraConfigs = Map("<conf-key>" -> dbutils.secrets.get(scope = "<scope-name>", key = "<key-name>")))
```  

Access
```scala
val df = spark.read.format("text").load("/mnt/<mount-name>/...")
val df = spark.read.format("text").load("dbfs:/<mount-name>/...")
```
SQL
```sql
-- SQL
CREATE DATABASE <db-name> LOCATION "/mnt/<mount-name>"
```
Umount
```
dbutils.fs.unmount("/mnt/<mount-name>")
```

## Azure Databricks also supports the following Azure data sources:

|Name|Desc|
|-|-|
|Azure Data Lake Storage Gen1||
|Azure Data Lake Storage Gen2|Expensive?
|Azure Cosmos DB||
|Azure Synapse Analytics||
