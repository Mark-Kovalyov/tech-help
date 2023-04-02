#Databricks CLI

https://docs.databricks.com/dev-tools/cli/index.html

## CLI

* https://docs.microsoft.com/en-us/azure/databricks/dev-tools/cli/clusters-cli

```
export DATABRICKS_CONFIG_FILE=<path-to-file>
```

```
$ pip install databricks-cli

$ databricks
Usage: databricks [OPTIONS] COMMAND [ARGS]...

Options:
  -v, --version   0.16.6
  --debug         Debug Mode. Shows full stack trace on error.
  --profile TEXT  CLI connection profile to use. The default profile is
                  "DEFAULT".
  -h, --help      Show this message and exit.

Commands:
  cluster-policies  Utility to interact with Databricks cluster policies.
  clusters          Utility to interact with Databricks clusters.
  configure         Configures host and authentication info for the CLI.
  fs                Utility to interact with DBFS.
  groups            Utility to interact with Databricks groups.
  instance-pools    Utility to interact with Databricks instance pools.
  jobs              Utility to interact with jobs.
  libraries         Utility to interact with libraries.
  pipelines         Utility to interact with the Databricks Delta Pipelines.
  repos             Utility to interact with Repos.
  runs              Utility to interact with the jobs runs.
  secrets           Utility to interact with Databricks secret API.
  stack             [Beta] Utility to deploy and download Databricks resource
                    stacks.
  tokens            Utility to interact with Databricks tokens.
  workspace         Utility to interact with the Databricks workspace.

```
### Configure
```
dbfs configure --token
```
### Listing folders
```
dbfs ls
```
### List clusters in JSON format
```
databricks clusters list --output TABLE
databricks clusters list --output JSON | jq '.clusters[].cluster_id'
```

## Copy file
```
dbfs cp --profile PROD dbfs://mnt/...../file.csv .
```
