package producer

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}


object productor {
	def main(args: Array[String]): Unit = {
		// Properties of the productor
		val properties = new Properties()
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
		properties.put(ProducerConfig.ACKS_CONFIG, "all")
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

		// Productor= Key: String, Value: String
		val producer = new KafkaProducer[String, String](properties)
		
		// Name of topic
		val topic = "KafkaTopic"
		// Custom Callback
		val callback = new TestCallback()


		for (i <- 1 to 50) {
			val record = new ProducerRecord(topic, "key " + i, "value " + i)
			producer.send(record, callback)
			Thread.sleep(500)
		}

		producer.close()
	}
}


