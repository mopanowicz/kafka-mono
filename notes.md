# notes

https://leanpub.com/esversioning/read

## Kafka connect setup

https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/10.7.6/confluentinc-kafka-connect-jdbc-10.7.6.zip

https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-avro-converter/versions/7.6.1/confluentinc-kafka-connect-avro-converter-7.6.1.zip

https://docs.confluent.io/platform/current/schema-registry/connect.html

```bash
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic connect-offsets
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic connect-configs
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic connect-status
```

```bash
./bin/kafka-topics.sh --create --topic connect-offsets --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
./bin/kafka-topics.sh --create --topic connect-configs --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
./bin/kafka-topics.sh --create --topic connect-status --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
```

```bash
./bin/connect-distributed.sh config/connect-distributed.properties
```

## Issues

https://github.com/confluentinc/schema-registry/issues/1604
