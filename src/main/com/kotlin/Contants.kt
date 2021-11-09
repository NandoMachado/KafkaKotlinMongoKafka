import java.time.Instant

class Constants {
    val bootstrapServer : String = ReadFromApplicationProperties().read("bootstrapServer")
    var nowTimeStamp = Instant.now().toString()
}



