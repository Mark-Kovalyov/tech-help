# Spark structured streaming from Delta table

* Table streaming reads and writes https://docs.delta.io/latest/delta-streaming.html

```
spark.readStream.format("delta")
  .load("/tmp/delta/events")

import io.delta.implicits._
spark.readStream.delta("/tmp/delta/events")
```

## Limit input rate

* maxFilesPerTrigger: How many new files to be considered in every micro-batch. The default is 1000.

* maxBytesPerTrigger: How much data gets processed in each micro-batch. This option sets a “soft max”, meaning that a batch processes approximately this amount of data and may process more than the limit in order to make the streaming query move forward in cases when the smallest input unit is larger than this limit. If you use Trigger.Once for your streaming, this option is ignored. This is not set by default.

## Ignore updates and deletes


* ignoreDeletes: ignore transactions that delete data at partition boundaries.

* ignoreChanges: re-process updates if files had to be rewritten in the source table due to a data changing operation such as UPDATE, MERGE INTO, DELETE (within partitions), or OVERWRITE. Unchanged rows may still be emitted, therefore your downstream consumers should be able to handle duplicates. Deletes are not propagated downstream. ignoreChanges subsumes ignoreDeletes. Therefore if you use ignoreChanges, your stream will not be disrupted by either deletions or updates to the source table.
