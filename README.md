## Kafka Simple Template

Sample project using [Apache Kafka](https://kafka.apache.org/) with [Scala](https://www.scala-lang.org/). It consist in a message producer, that generate incremental message and the consummer just print it on screen.


To run this project you need to run start Kafka, using the following commands must be enought:

```bash
cd $KAFKA_HOME

# Run Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties &

# Run Kafka
bin/kafka-server-start.sh config/server.properties &

```
