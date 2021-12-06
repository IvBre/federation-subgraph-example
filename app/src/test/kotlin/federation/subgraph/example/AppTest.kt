package federation.subgraph.example

import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.extensions.print
import com.expediagroup.graphql.generator.federation.FederatedSchemaGeneratorConfig
import com.expediagroup.graphql.generator.federation.FederatedSchemaGeneratorHooks
import com.expediagroup.graphql.generator.federation.toFederatedSchema
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test fun verifyGeneratedSchema() {
        val expectedSchema =
            """
            schema {
              query: Query
            }
            
            "Directs the executor to include this field or fragment only when the `if` argument is true"
            directive @include(
                "Included when true."
                if: Boolean!
              ) on FIELD | FRAGMENT_SPREAD | INLINE_FRAGMENT
            
            "Directs the executor to skip this field or fragment when the `if`'argument is true."
            directive @skip(
                "Skipped when true."
                if: Boolean!
              ) on FIELD | FRAGMENT_SPREAD | INLINE_FRAGMENT
            
            "Marks the field, argument, input field or enum value as deprecated"
            directive @deprecated(
                "The reason for the deprecation"
                reason: String = "No longer supported"
              ) on FIELD_DEFINITION | ARGUMENT_DEFINITION | ENUM_VALUE | INPUT_FIELD_DEFINITION
            
            "Exposes a URL that specifies the behaviour of this scalar."
            directive @specifiedBy(
                "The URL that specifies the behaviour of this scalar."
                url: String!
              ) on SCALAR
            
            "Marks target field as external meaning it will be resolved by federated schema"
            directive @external on FIELD_DEFINITION
            
            "Specifies required input field set from the base type for a resolver"
            directive @requires(fields: _FieldSet) on FIELD_DEFINITION
            
            "Specifies the base type field set that will be selectable by the gateway"
            directive @provides(fields: _FieldSet) on FIELD_DEFINITION
            
            "Space separated list of primary keys needed to access federated object"
            directive @key(fields: _FieldSet) on OBJECT | INTERFACE
            
            "Marks target object as extending part of the federated schema"
            directive @extends on OBJECT | INTERFACE
            
            type Query @extends {
              _service: _Service
              example(text: String!): String!
            }
            
            type _Service {
              sdl: String!
            }
            
            "Federation type representing set of fields"
            scalar _FieldSet
            """.trimIndent()

        val config = FederatedSchemaGeneratorConfig(
            supportedPackages = listOf("federation.subgraph.example"),
            hooks = FederatedSchemaGeneratorHooks(emptyList())
        )

        val schema = toFederatedSchema(config = config, listOf(TopLevelObject(ExampleQuery())))
        assertEquals(expectedSchema, schema.print().trim())
    }
}
