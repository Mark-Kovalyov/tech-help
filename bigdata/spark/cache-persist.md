# Cache and Persist

```
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, SaveMode, SparkSession, types}
import org.apache.spark.storage.StorageLevel

val geo : DataFrame = spark.read.option("header","true").option("inferSchema", "true").csv("/bigdata/db/geo/GeoIPCity.utf-8.csv.bz2")

geo.printSchema()

val geoSinglePart = geo.coalesce(1)

spark.time(geoSinglePart.persist(StorageLevel.DISK_ONLY))

spark.time(geoSinglePart.write.mode(SaveMode.Overwrite).parquet("/bigdata/db/geo/GeoIPCity-parquet"))

spark.time(geoSinglePart.write.mode(SaveMode.Overwrite).orc("/bigdata/db/geo/GeoIPCity-orc"))
```
