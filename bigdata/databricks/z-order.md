# Z-order

```
OPTIMIZE [db_name.]table_name [WHERE predicate]
  [ZORDER BY (col_name1, col_name2, ...)]
```

```
OPTIMIZE events

OPTIMIZE events WHERE date >= '2017-01-01'

OPTIMIZE events
WHERE date >= current_timestamp() - INTERVAL 1 day
ZORDER BY (eventType)
```
