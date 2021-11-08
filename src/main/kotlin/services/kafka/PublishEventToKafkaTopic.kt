package services.kafka

import models.KafkaProducerModel
import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PublishEventToKafkaTopic {
    fun publish() {
        val producer = KafkaProducerModel().createProducer("localhost:29092")
        val record = ProducerRecord<String, String>("outboundTopic", "Outbound topic event")
        val logger: Logger = LoggerFactory.getLogger("outboundProducer")

        producer.send(record) { recordMetadata, e: Exception? ->
            if (e == null) {
                logger.info(
                    "Successfully received the details as \n" +
                            "Topic: ${ recordMetadata.topic() } \n" +
                            "Offset: ${ recordMetadata.offset() } \n" +
                            "Timestamp: ${ recordMetadata.timestamp() }"
                )
            } else {
                logger.error("Can't produce, getting error $e")
            }
        }
        producer.flush()
        producer.close()
    }
}

fun main(){
    PublishEventToKafkaTopic().publish()
}