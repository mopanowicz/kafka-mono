# notes

Generate source code

```
mvn generate-sources
```

Register schemas configured in pom.xml

```
mvn schema-registry:register -Dschema.registry.url=http://kafka.local:8081
```
