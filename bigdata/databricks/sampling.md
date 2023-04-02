# Table sampling

## Syntax
```
TABLESAMPLE ( { percentage PERCENT ) |
                num_rows ROWS |
                BUCKET fraction OUT OF total } )
            [ REPEATABLE ( seed ) ]
```      
Sample:

```
SELECT * FROM test TABLESAMPLE (30 PERCENT) REPEATABLE (123);
SELECT * FROM test TABLESAMPLE (5 ROWS);
SELECT * FROM test TABLESAMPLE (BUCKET 4 OUT OF 10);
```      
