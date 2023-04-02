# DB Utils

https://docs.databricks.com/dev-tools/databricks-utils.html#dbutils-fs

## Mount

```
val AwsBucketName = "my-bucket"
val MountName = "s3-my-bucket"

dbutils.fs.mount(s"s3a://$AwsBucketName", s"/mnt/$MountName")
```

## Copy table (example from devops)

```sql
%sql
desc table extended scott.emp
```

```bash
cp -r /dbfs/mnt/staging/warehouse/scott.DB/emp /dbfs/mnt/staging/warehouse/scott.DB/emp
```

## Table to dbfs

```

```

## Estimate physical size (Python)
```python
filelist = dbutils.fs.ls("wasbs://data@......blob.core.windows.net/warehouse/user.db/tab1")

sum = 0

for i in filelist:
  sum = sum + i.size

println("sum = ", sum / 1024 / 1024 / 1024 , "G")  
```
second aproach:
```python
def format_file_size(size, i = 0) :  
  mlist = ['bytes','K','M','G','T']
  if size < 1024 or i == len(mlist)-1 :    
    return str(size) + ' ' + mlist[i]
  else :    
    return format_file_size(size // 1024, i + 1)

sum = reduce(lambda x, y:x+y, map(lambda x: x.size, filelist))
println("sum = ", sum / 1024 / 1024 / 1024 , "G")  


def dbrx_table_size(path) :
  // TODO:

```

## Estimate physical size (Scala)
```scala
def format_file_size(size : BigInt, m : List[String] = List("bytes","K","M","G","T")) : String = {
  if (size < 1024 || m.tail == Nil) "" + size + " " + m.head else format_file_size(size / 1024, m.tail)
}

def dbrx_table_size(path : String) : String = {
  val filelist = dbutils.fs.ls(path)
  format_kilo(filelist.map(_.size).sum)
}
```







"Классная зубная паста WILD NATURE со вкусом орехов".

pattern =
