import services.kafka.ConsumeFromKafkaTopic

fun main(){
    Constants()
    ConsumeFromKafkaTopic().listen()
}