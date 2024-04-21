# notes

```bash
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic message_dlq
```

```bash
./bin/kafka-topics.sh --create --topic message_dlq --bootstrap-server kafka.local:9092
```