# notes

```bash
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic simple_one-dlq
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic logical_one-dlq
```

```bash
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --create --topic simple_one-dlq
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --create --topic logical_one-dlq
```

