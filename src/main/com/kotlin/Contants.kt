import java.time.Instant

class Constants {
    val kafkabootstrapServer : String = ReadFromApplicationProperties().read("bootstrapServer")
    var nowTimeStamp = Instant.now().toString()

}



