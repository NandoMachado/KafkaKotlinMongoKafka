import java.io.File
import java.io.FileInputStream
import java.util.*

class ReadApplicationProperties {

    fun print() {
        val props = Properties()
        FileInputStream(File("src/main/resources/application.properties")).use { props.load(it) }
        props.stringPropertyNames()
            .associateWith { props.getProperty(it) }
            .forEach{println(it)}
    }
}