# Upsert

```python
deltaTab.merge(df2, ....)
  .whenMatchedUpdate(set="....")
  .whenNotMatchedUpdate(values="...")
  .execute()
```
