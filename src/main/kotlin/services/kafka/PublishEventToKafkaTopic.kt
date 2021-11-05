package services.kafka

import models.CreateKafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class PublishEventToKafkaTopic {
    fun publish(){
        val producer = CreateKafkaProducer().createProducer()

        // Creating producer record
        val record = ProducerRecord<String, String>("topic-name-test", "This is my first topic")

        // Sending record
        producer.send(record)
        producer.flush()
        producer.close()
    }
}

fun main(){
    PublishEventToKafkaTopic().publish()
}