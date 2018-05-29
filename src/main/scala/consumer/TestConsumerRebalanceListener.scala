package consumer

import java.util

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.TopicPartition

class TestConsumerRebalanceListener extends ConsumerRebalanceListener {
	override def onPartitionsRevoked(partitions: util.Collection[TopicPartition]): Unit =
		println(s"Called onPartitionsRevoked with partitions: $partitions")


	override def onPartitionsAssigned(partitions: util.Collection[TopicPartition]): Unit =
		println(s"Called onPartitionsAssigned with partitions: $partitions")


}