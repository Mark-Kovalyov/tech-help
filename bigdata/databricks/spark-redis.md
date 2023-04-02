# Databricks-Redis

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>4.2.3</version>
</dependency>

<dependency>
  <groupId>com.redislabs</groupId>
  <artifactId>spark-redis</artifactId>
  <version>2.4.1</version>
</dependency>
```

## Shell
```bash
spark-shell --packages "com.redislabs:spark-redis:2.4.1"
```

## Scala : Push DataFrame/RDD into redis (Case 1)

Configure
```scala
import com.redislabs.provider.redis._

val redisConf = new RedisConfig(new RedisEndpoint("host", 5555, "xxx", 0, 20000))
```

```scala
val df = ....

df.createOrReplaceTempView("src")

val rdd : RDD[(String,String)] = spark.sql("SELECT key,value FROM src").rdd.map(
    case Row(key : String, value : String) => key -> value
  )

val TTL = 1000

sc.toRedisKV(rdd, TTL)(redisConf)
```
## Python:

### Install Redis support

* Compute -> Libraries -> Install new -> Type = Pypi -> redis

### Connect and Run
```python
import redis
import sys

r = redis.Redis(host='host.net', ssl=True, port=6380, password='****')

r.set('key1','value1')
r.get('key1')
```
