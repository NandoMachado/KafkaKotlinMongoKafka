package services.kafka

import models.KafkaProducerModel
import org.apache.kafka.clients.producer.ProducerRecord

class PublishEventToKafkaTopic {
    fun publish(){
        val producer = KafkaProducerModel().createProducer()

        val record = ProducerRecord<String, String>("topic-name-test", "This is my first topic")

        producer.send(record)
        producer.flush()
        producer.close()
    }
}

fun main(){
    PublishEventToKafkaTopic().publish()
}