package producer

import org.apache.kafka.clients.producer.{Callback, RecordMetadata}


/**
  * Callback to check send message
  */
class TestCallback extends Callback {
	override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
		exception match {
			case null => println(s"Sent message to topic: ${metadata.topic()} partition: ${metadata.partition()} offset: ${metadata.offset()}")
			case _ =>
				println(s"Error while producing message to topic: $metadata")
				exception.printStackTrace()
		}
	}
}