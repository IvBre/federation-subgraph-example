package federation.subgraph.example

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

@Component
class ExampleQuery: Query {
    @Suppress("unused")
    fun example(
        text: String
    ): String = "Hello $text !"
}