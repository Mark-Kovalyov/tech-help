# Databricks secret API

## Dbutils (Python)

```python
dbutils.secrets.listScopes()
dbutils.secrets.list("my-scope")
dbutils.secrets.get(scope="my-scope", key="db1")
```

Extract endpoint and key from cosmos connection string

```python
# AccountEndpoint=https://host:443/;AccountKey=********************==

def extractEndpointAndKey(conn_str : str):
	endpoint = None
	key = None
	for i in conn_str.split(";")
      if i.startswith("AccountEndpoint="):
      	endpoint = i[len("AccountEndpoint="):]
	  if i.startswith("AccountKey="):
      	endpoint = i[len("AccountKey="):]      	
```

Scramle the key
```python
def scramble(s : str) -> str:
  buf = ""
  for ch in range(0, len(s)):
  	buf = buf + s[ch] + "_"
  return buf	
```

## Microsoft Azure Key Vault Secrets API for Python (4.5.1)

```bash
pip install azure-keyvault-secrets
pip install azure-identity
```

### List secrets
```python
import os
from azure.keyvault.secrets import SecretClient
from azure.identity import DefaultAzureCredential

credential = DefaultAzureCredential()
client = SecretClient(vault_url="https://mtn.vault.azure.net", credential=credential)
props = client.list_properties_of_secrets()

for prop in props:
  print(prop.name)
```

### Retrieve
```python
import os
from azure.keyvault.secrets import SecretClient
from azure.identity import DefaultAzureCredential

keyVaultName = os.environ["KEY_VAULT_NAME"]
KVUri = f"https://{keyVaultName}.vault.azure.net"

credential = DefaultAzureCredential()
client = SecretClient(vault_url=KVUri, credential=credential)
retrieved_secret = client.get_secret("pwd1")

print(f"Your secret is '{retrieved_secret.value}'.")
```

### Create vault with AZ

```bash
$ az version --output table
Azure-cli    Azure-cli-core    Azure-cli-telemetry
-----------  ----------------  ---------------------
2.40.0       2.40.0            1.0.8


$ az keyvault list
[]

$ az group create --name mtn-rg --location germanywestcentral
....

$ az keyvault create --name mtn-vault --resource-group mtn-rg
....

$ az keyvault list --output table
Location            Name       ResourceGroup
------------------  ---------  ---------------
germanywestcentral  mtn-vault  mtn-rg
```
(manually add jdbc secret via UI)
```bash
$ az keyvault secret list --vault-name mtn-vault --output yaml
- attributes:
    created: '2022-09-20T22:47:21+00:00'
    enabled: true
    expires: '2022-12-31T23:44:48+00:00'
    notBefore: null
    recoveryLevel: Purgeable
    updated: '2022-09-20T22:47:21+00:00'
  contentType: ''
  id: https://mtn-vault.vault.azure.net/secrets/jdbc1
  managed: null
  name: jdbc1
  tags: {}
```
download secret 
```bash
az keyvault secret download --file /tmp/out --name jdbc1 --vault-name mtn-vault 
```


### EnvironmentCredential?

Cases:
* AZURE_CLIENT_ID, AZURE_TENANT_ID, AZURE_CLIENT_SECRET
* AZURE_CLIENT_ID, AZURE_TENANT_ID, AZURE_CLIENT_CERTIFICATE_PATH
* AZURE_USERNAME, AZURE_PASSWORD

### Update
```python
...
updated_secret_props = client.update_secret_properties("pwd2", "text/plain", enabled=False)
```

## Show OS environment

```
import os

print("scala version       = " + os.environ['SCALA_VERSION'])
print("spark scala version = " + os.environ['SPARK_SCALA_VERSION'])
print("databricks runtime  = " + os.environ['DATABRICKS_RUNTIME_VERSION'])
print("worker memory       = " + os.environ['SPARK_WORKER_MEMORY'])
print("buffer              = " + os.environ['SPARK_BUFFER_SIZE'])

def 
```

## Show Spark variable

```python
spark.conf.get("spark.<name>")
```

```python
display(sql("SET -v"))
```
