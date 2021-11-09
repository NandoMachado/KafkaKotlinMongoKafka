import java.time.Instant

class Constants {
    val kafkabootstrapServer : String = ReadFromApplicationProperties().read("bootstrapServer")
    var nowTimeStamp = Instant.now().toString()
    val inboundTopic = ReadFromApplicationProperties().read("inbound_topic_name")
    val outboundTopic = ReadFromApplicationProperties().read("outbound_topic_name")
    val mongoHost = ReadFromApplicationProperties().read("mongoHost")
    val mongoPort = ReadFromApplicationProperties().read("mongoPort").toInt()

}



