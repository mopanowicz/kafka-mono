# notes

```bash
docker run -d -p 8080:8080 --net=host \
  -e schemaRegistryUrl="http://kafka-tools.local:8081" \
  -e bootstrapServers="kafka.local:9092" \
  -e kouncil.auth.active-provider="inmemory" \
  consdata/kouncil:latest
```
