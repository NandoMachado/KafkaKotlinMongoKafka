package services.kafka

import models.KafkaProducerModel
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant

class PublishEventToKafkaTopic {

    private val now = Instant.now()
    private val producer = KafkaProducerModel().createProducer("localhost:29092")
    private val logger: Logger = LoggerFactory.getLogger("producer")
    private val record = ProducerRecord<String, String>("outboundTopic","Outbound topic event: $now")

    fun publish() {
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