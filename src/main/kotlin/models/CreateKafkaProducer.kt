package models

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class CreateKafkaProducer {
    fun createProducer(brokers : String) : Producer<String, String> {
        val props = Properties()
        props["bootstrap-servers"] = brokers
        props["key-serializer"] = StringSerializer::class.java.canonicalName
        props["value.serializer"] = StringSerializer::class.java.canonicalName
        return KafkaProducer(props)
    }
}