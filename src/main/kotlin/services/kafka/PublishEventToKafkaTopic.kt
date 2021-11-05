package services.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class PublishEventToKafkaTopic {
    fun publish(){
        // Config properties
        val bootstrapServers = "localhost:29092"
        val properties = Properties()
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.canonicalName)
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.canonicalName)

        // Creating producer
        val producer = KafkaProducer<String, String>(properties)

        // Creating producer record
        val record = ProducerRecord<String, String>("topic-name-test", "This is my first topic")

        // Sending record
        producer.send(record)
        producer.flush()
        producer.close()
    }
}
//
//fun main(){
//    PublishEventToKafkaTopic().publish()
//}