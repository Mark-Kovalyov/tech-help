# Apache parquet

* Bugtracker:  https://issues.apache.org/jira/projects/PARQUET/issues/

## File header

```
00000000  50 41 52 31 15 00 15 8c  cf 14 15 a4 d8 0a 15 b3  |PAR1............|

```

## Schema samples:
```
message spark_schema {
  optional binary startIpNum (UTF8);
  optional binary endIpNum (UTF8);
  optional binary country (UTF8);
  optional binary region (UTF8);
  optional binary city (UTF8);
  optional binary postalCode (UTF8);
  optional double latitude;
  optional double longitude;
  optional int32 dmaCode;
  optional int32 areaCode;
}
```
```
message spark_schema {
  optional binary id (UTF8);
  optional binary uuid (UTF8);
}

```
## Metadata samples:
```
creator:     parquet-mr version 1.12.2 (build 77e30c8093386ec52c3cfa6c34b7ef3321322c94)
extra:       org.apache.spark.version = 3.2.1
extra:       org.apache.spark.sql.parquet.row.metadata = {"type":"struct","fields":[{"name":"star [more]...

file schema: spark_schema
-----------------------------------------------------------------------------------------------------------
startIpNum:  OPTIONAL BINARY O:UTF8 R:0 D:1
endIpNum:    OPTIONAL BINARY O:UTF8 R:0 D:1
country:     OPTIONAL BINARY O:UTF8 R:0 D:1
region:      OPTIONAL BINARY O:UTF8 R:0 D:1
city:        OPTIONAL BINARY O:UTF8 R:0 D:1
postalCode:  OPTIONAL BINARY O:UTF8 R:0 D:1
latitude:    OPTIONAL DOUBLE R:0 D:1
longitude:   OPTIONAL DOUBLE R:0 D:1
dmaCode:     OPTIONAL INT32 R:0 D:1
areaCode:    OPTIONAL INT32 R:0 D:1

row group 1: RC:5748952 TS:268721793
-----------------------------------------------------------------------------------------------------------
startIpNum:   BINARY SNAPPY DO:0 FPO:4 SZ:29879981/95061472/3.18 VC:5748952 ENC:RLE,PLAIN,BIT_PACKED
endIpNum:     BINARY SNAPPY DO:0 FPO:29879985 SZ:29560275/98721184/3.34 VC:5748952 ENC:RLE,PLAIN,BIT_PACKED
country:      BINARY SNAPPY DO:59440260 FPO:59441269 SZ:238945/319285/1.34 VC:5748952 ENC:RLE,PLA [more]...
region:       BINARY SNAPPY DO:59679205 FPO:59680806 SZ:3387742/3880306/1.15 VC:5748952 ENC:RLE,P [more]...
city:         BINARY SNAPPY DO:63066947 FPO:63830944 SZ:16754942/38450308/2.29 VC:5748952 ENC:RLE [more]...
postalCode:   BINARY SNAPPY DO:79821889 FPO:80074482 SZ:4367685/4559653/1.04 VC:5748952 ENC:RLE,P [more]...
latitude:     DOUBLE SNAPPY DO:84189574 FPO:84475897 SZ:10224083/11723410/1.15 VC:5748952 ENC:RLE [more]...
longitude:    DOUBLE SNAPPY DO:94413657 FPO:94743365 SZ:10389554/11838234/1.14 VC:5748952 ENC:RLE [more]...
dmaCode:      INT32 SNAPPY DO:104803211 FPO:104804077 SZ:1250198/1635819/1.31 VC:5748952 ENC:RLE, [more]...
areaCode:     INT32 SNAPPY DO:106053409 FPO:106054892 SZ:2300357/2532122/1.10 VC:5748952 ENC:RLE,
```

```
creator:     parquet-mr version 1.12.2 (build 77e30c8093386ec52c3cfa6c34b7ef3321322c94)
extra:       org.apache.spark.version = 3.2.1
extra:       org.apache.spark.sql.parquet.row.metadata = {"type":"struct","fields":[{"name":"id", [more]...

file schema: spark_schema
-----------------------------------------------------------------------------------------------------------
id:          OPTIONAL BINARY O:UTF8 R:0 D:1
uuid:        OPTIONAL BINARY O:UTF8 R:0 D:1

row group 1: RC:999999 TS:49892538
-----------------------------------------------------------------------------------------------------------
id:           BINARY SNAPPY DO:0 FPO:4 SZ:4328893/9890731/2.28 VC:999999 ENC:RLE,PLAIN,BIT_PACKED
uuid:         BINARY SNAPPY DO:0 FPO:4328897 SZ:36101507/40001807/1.11 VC:999999 ENC:RLE,PLAIN,BIT_PACKED
```

## Data Organization

* Row-groups (128M) (set of column chunks)
  * Column Chunks (set of pages)
    * Pages(default 1Mb)
      * metadata: Min, Max, Count
      * Rep/dev levels
      * Encoded values  

## Encodings:

* PLAIN
* RLE_DICTIONARY

## Support Java libraries:

### Main (Parquet-MR)

```
<dependencies>
  <dependency>
    <groupId>org.apache.parquet</groupId>
    <artifactId>parquet-common</artifactId>
    <version>1.12.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.parquet</groupId>
    <artifactId>parquet-encoding</artifactId>
    <version>1.12.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.parquet</groupId>
    <artifactId>parquet-column</artifactId>
    <version>1.12.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.parquet</groupId>
    <artifactId>parquet-hadoop</artifactId>
    <version>1.12.0</version>
  </dependency>
</dependencies>
```

### From twitter:

```
<dependency>
  <groupId>com.twitter</groupId>
  <artifactId>parquet-format</artifactId>
  <version>${parquet.format.version}</version>
</dependency>
<dependency>
  <groupId>com.twitter</groupId>
  <artifactId>parquet-hadoop</artifactId>
  <version>${parquet.mr.version}</version>
</dependency>
<dependency>
  <groupId>org.apache.hadoop</groupId>
  <artifactId>hadoop-core</artifactId>
  <version>1.2.1</version>
</dependency>
```

### From io.delta

```
<dependency>
  <groupId>io.delta</groupId>
  <artifactId>delta-standalone_2.13</artifactId>
  <version>0.4.0</version>
</dependency>

<dependency>
  <groupId>org.apache.hadoop</groupId>
  <artifactId>hadoop-client</artifactId>
  <version>3.3.2</version>
</dependency>

<dependency>
  <groupId>org.apache.parquet</groupId>
  <artifactId>parquet-hadoop</artifactId>
  <version>1.12.2</version>
</dependency>
```
