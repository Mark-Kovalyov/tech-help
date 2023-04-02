# Delta standalone reader


* https://databricks.com/blog/2020/12/22/natively-query-your-delta-lake-with-scala-java-and-python.html
* Delta Standalone Reader - https://github.com/delta-io/connectors/wiki/Delta-Standalone-Reader

```
libraryDependencies ++= Seq(
  "io.delta" %% "delta-standalone" % "0.2.0",
  "org.apache.hadoop" % "hadoop-client" % "2.7.2",
  "org.apache.parquet" % "parquet-hadoop" % "1.10.1")
```

metadata
```
import io.delta.standalone.DeltaLog;
import io.delta.standalone.Snapshot;
import io.delta.standalone.data.CloseableIterator;
import io.delta.standalone.data.RowRecord;

import org.apache.hadoop.conf.Configuration;

DeltaLog log = DeltaLog.forTable(new Configuration(), "[DELTA LOG LOCATION]");

// Returns the current snapshot
log.snapshot();

// Returns the version 1 snapshot
log.getSnapshotForVersionAsOf(1);

// Returns the snapshot version
log.snapshot.getVersion();

// Returns the number of data files
log.snapshot.getAllFiles().size();
```
delta table
```
// Create a closeable iterator
CloseableIterator iter = snapshot.open();

RowRecord row = null;
int numRows = 0;

// Schema of Delta table is {long, long, string}
while (iter.hasNext()) {
row = iter.next();
      numRows++;

      Long c1 = row.isNullAt("c1") ? null : row.getLong("c1");
      Long c2 = row.isNullAt("c2") ? null : row.getLong("c2");
      String c3 = row.getString("c3");
      System.out.println(c1 + " " + c2 + " " + c3);
}
```
