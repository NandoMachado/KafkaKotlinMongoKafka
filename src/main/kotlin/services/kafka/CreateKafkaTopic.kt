package services.kafka

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.errors.TopicExistsException
import java.util.*
import java.util.concurrent.ExecutionException

class CreateKafkaTopic {
    fun createTopic(topic: String,
                    partitions: Int,
                    replication: Short,
                    dockerConfig: Properties
    ) {
        val newTopic = NewTopic(topic, partitions, replication)

        try {
            with(AdminClient.create(dockerConfig)) {
                createTopics(listOf(newTopic)).all().get()
            }
        } catch (e: ExecutionException) {
            if (e.cause !is TopicExistsException) throw e
        }
    }
}