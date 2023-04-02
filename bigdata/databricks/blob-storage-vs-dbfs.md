# Blob Storage vs DBFS filesystem


Recommended: wasbs://data@mpcosmuse2sephptcxdbsa.blob.core.windows.net/warehouse/data_analytics.DB

Mounted : /mnt/data_analytics

```
mount(
   source: String, 
   mountPoint: String, 
   encryptionType: String = "", 
   owner: String = null, 
   extraConfigs: Map = Map.empty[String, String]): boolean 
     -> Mounts the given source directory into DBFS at the given mount point

```

```
"Azure Blob Storage contains three types of blobs: Block, Page and Append. A block is a single unit in a Blob. A Blob can contain many blocks but not more than 50,000 blocks per Blob. This means you can split a Blob into 50,000 blocks to upload to Azure Blobs storage. The minimum size of a block is 64KB and maximum is 100 MB. If you look at (for example .NET library), one of the objects is BlockBlob which is part of CloudBlockBlob class. This class offers you tons of things to work with a Block Blob."

Is this the correct ansver - minimum is 64 kB ?

It also seems that FileShare disk cluster size is also 64 kB and cannot be changed ...
```

```
Azure Blob limits: Block blobs are optimized for uploading large amounts of data efficiently. Block blobs are composed of blocks, each of which is identified by a block ID. A block blob can include up to 50,000 blocks. Each block in a block blob can be a different size, up to the maximum size permitted for the service version in use. To create or modify a block blob, write a set of blocks via the Put Block operation and then commit the blocks to a blob with the Put Block List operation.

Blobs that are less than a certain size (determined by service version) can be uploaded in their entirety with a single write operation via Put Blob.

Storage clients default to a 128 MiB maximum single blob upload, settable in the Azure Storage client library for .NET version 11 by using the SingleBlobUploadThresholdInBytes property of the BlobRequestOptions object. When a block blob upload is larger than the value in this property, storage clients break the file into blocks. You can set the number of threads used to upload the blocks in parallel on a per-request basis using the ParallelOperationThreadCount property of the BlobRequestOptions object. 
```

```
val configs = Map(
  "fs.azure.account.auth.type"              -> "OAuth",
  "fs.azure.account.oauth.provider.type"    -> "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider",
  "fs.azure.account.oauth2.client.id"       -> "<application-id>",
  "fs.azure.account.oauth2.client.secret"   -> dbutils.secrets.get(scope="<scope-name>",key="<service-credential-key-name>"),
  "fs.azure.account.oauth2.client.endpoint" -> "https://login.microsoftonline.com/<directory-id>/oauth2/token")
// Optionally, you can add <directory-name> to the source URI of your mount point.
dbutils.fs.mount(
  source = "abfss://<container-name>@<storage-account-name>.dfs.core.windows.net/",
  mountPoint = "/mnt/<mount-name>",
  extraConfigs = configs)
```






hit_data:
 location : dbfs:/mnt/prd-cxdb/warehouse/SDPDB.DB/hit_data

clickstream_product_merchandise:
 location : wasbs://data@mpcosmuse2sephptcxdbsa.blob.core.windows.net/warehouse/SDPDB.DB/product_merchandise

 snapshot :
   logical :  9461327331215
   physical : 

 delta-log :

   logical :     5021642440
   physical :   27917287424 (hdfs 128M)

bc_brand_affinity_history:
 
  snapshot:
    files :                  176875
    avg_file_size :       5 551 093
    logical:        981 849 608 776  (900Gb)
    physical:    12 083 957 596 160  (12Tb)
                                                  981849608776.0 / 176875.0

## Top tables

|Table                           | size (snapshot)    | delta log    | Total          | log/size ratio | files  | 97th percentile length  | Approx physical size |
|--------------------------------|--------------------|--------------|----------------|----------------|--------|-------------------------|----------------------|
|hit_data                        | 15489025776123     | 255921228310 | 15744947004433 | 0.0162         |        |                         |
|clickstream_product_merchandise |  9461327331215     |   5021642440 |  9466348973655 | 0.0005         |
|bc_brand_affinity_history       |   981849608776     |    146906729 |   981996515505 | 0.0001         | 176875 |

                                     
                                     





