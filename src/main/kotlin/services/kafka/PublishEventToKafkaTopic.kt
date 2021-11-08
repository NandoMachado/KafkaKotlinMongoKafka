package services.kafka

import models.KafkaProducerModel
import org.apache.kafka.clients.producer.ProducerRecord

class PublishEventToKafkaTopic {
    fun publish(){
        val producer = KafkaProducerModel().createProducer("localhost:29092")
        val record = ProducerRecord<String, String>("outboundTopic", "Outbound topic event")

        producer.send(record)
        producer.flush()
        producer.close()
    }
}

fun main(){
    PublishEventToKafkaTopic().publish()
}