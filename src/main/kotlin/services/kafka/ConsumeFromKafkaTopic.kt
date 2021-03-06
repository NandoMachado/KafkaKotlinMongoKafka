package services.kafka

import Constants
import models.KafkaConsumerModel
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

class ConsumeFromKafkaTopic {

    private val logger: Logger = LoggerFactory.getLogger("consumer")
    private val consumer = KafkaConsumerModel().createConsumer(groupID = "listener")

    fun listen() {
        consumer.subscribe(listOf("${Constants().inboundTopic}"))
        while (true) {
            val consumerRecords : ConsumerRecords<String, String> = consumer.poll(Duration.ofMillis(100))
            for (record in consumerRecords) {
                logger.info("Key: ${record.key()}, Value: ${record.value()}.")
                logger.info("Partition: ${record.partition()}, Offset: ${record.offset()}.")
//                TODO: SaveToDB()
                PublishEventToKafkaTopic().publish(record.value())
            }
        }
    }
}