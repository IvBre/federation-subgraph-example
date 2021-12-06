package federation.subgraph.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

@SpringBootApplication
open class App
