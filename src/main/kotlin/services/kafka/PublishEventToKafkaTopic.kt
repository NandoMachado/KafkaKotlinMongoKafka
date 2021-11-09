package services.kafka

import Constants
import models.KafkaProducerModel
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PublishEventToKafkaTopic {

    private val producer = KafkaProducerModel().createProducer()
    private val logger: Logger = LoggerFactory.getLogger("producer")

    fun publish(message : String) {
        val record = ProducerRecord<String, String>(
            "${Constants().outboundTopic}",
            "${Constants().nowTimeStamp}: $message"
        )
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

//fun main(){
//    PublishEventToKafkaTopic().publish()
//    ConsumeFromKafkaTopic().listen()
//}