import java.io.File
import java.io.FileInputStream
import java.util.*

class ReadFromApplicationProperties {
    fun read(propertyValue : String = "") : String {
        val props = Properties()
        FileInputStream(File("src/main/resources/application.properties")).use { props.load(it) }
        props.stringPropertyNames()
            .associateWith { props.getProperty(it) }
        return props.getProperty(propertyValue)
    }
}