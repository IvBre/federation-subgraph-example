# Federation Subgraph example
Example repo needed for issue: https://github.com/ExpediaGroup/graphql-kotlin/issues/1310

In [the schema test](./app/src/test/kotlin/federation/subgraph/example/AppTest.kt) one can see that schema is generated 
with an optional `FieldSet` for built-in directives instead of required (with `!` sign).