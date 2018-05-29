package consumer

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

object basicConsumer {
	def main(args: Array[String]): Unit = {
		// Kafka Properties
		val consumerConfig = new Properties()
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group")
		consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")

		//Kafka consumer
		val consumer = new KafkaConsumer(consumerConfig)
		val rebalanceListener = new TestConsumerRebalanceListener()
		consumer.subscribe(Collections.singletonList("KafkaTopic"), rebalanceListener)

		// LifeCycle
		while (true) {
			val records = consumer.poll(1000) // Time to wait if data is not available in the buffer
			records.forEach(record =>
				println(s"Received Message Topic: ${record.topic()}," +
				        s"\npartition: ${record.partition()}," +
				        s"\noffset: ${record.offset()}," +
				        s"\nkey: ${record.key()}, value: ${record.value()}")
			)
			consumer.commitSync()
		}

	}



}

