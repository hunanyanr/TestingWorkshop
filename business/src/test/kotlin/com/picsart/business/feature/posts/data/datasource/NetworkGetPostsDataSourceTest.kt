package com.picsart.business.feature.posts.data.datasource

import arrow.core.Either
import com.picsart.business.arch.error.DataNotFound
import com.picsart.business.arch.error.Empty
import com.picsart.business.arch.error.ServerError
import com.picsart.business.feature.posts.data.model.PostDTO
import com.picsart.business.generators.dtoPost
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadGateway
import io.ktor.http.HttpStatusCode.Companion.GatewayTimeout
import io.ktor.http.HttpStatusCode.Companion.InsufficientStorage
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NotImplemented
import io.ktor.http.HttpStatusCode.Companion.ServiceUnavailable
import io.ktor.http.HttpStatusCode.Companion.VariantAlsoNegotiates
import io.ktor.http.HttpStatusCode.Companion.VersionNotSupported
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NetworkGetPostsDataSourceTest : BehaviorSpec({

    //This function creates a httpClient with mock responses
    fun createDataSourceFor(statusCode: HttpStatusCode, content: String = ""): NetworkGetPostsDataSource {
        val engine = MockEngine { request ->
            respond(
                status = statusCode,
                content = content,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClient(engine) {
            install(ContentNegotiation) {
                json()
            }
        }

        return NetworkGetPostsDataSource("localhost", client)
    }

    Given("A successful HttpResponse") {
        val statusCode = HttpStatusCode.OK
        When("It emits empty result") {
            val httpResult = emptyList<PostDTO>()
            val sut = createDataSourceFor(statusCode, Json.encodeToString(httpResult))
            And("Invoking the Datasource") {
                val result = sut.invoke()
                Then("It returns a Empty Failure") {
                    result shouldBe Either.Left(Empty)
                }
            }
        }
        When("It emits a  List of posts") {
            val httpResult = Arb.list(Arb.dtoPost()).next()
            val sut = createDataSourceFor(statusCode, Json.encodeToString(httpResult))
            And("Invoking the Datasource") {
                val result = sut.invoke()
                Then("It returns the list of posts") {
                    result shouldBe Either.Right(httpResult)
                }
            }
        }
    }

    val statusCode = listOf(
        InternalServerError,
        NotImplemented,
        BadGateway,
        ServiceUnavailable,
        GatewayTimeout,
        VersionNotSupported,
        VariantAlsoNegotiates,
        InsufficientStorage
    )

    statusCode.forEach {
        Given("A Server Error HttpResponseCode: $it") {
            val sut = createDataSourceFor(it)
            When("Invoking the datasource") {
                val result = sut.invoke()
                Then("It returns a a ServerError Failure") {
                    result shouldBe Either.Left(ServerError)
                }
            }
        }
    }

    Given("a Not found Server error (code 404)") {
        val sut = createDataSourceFor(HttpStatusCode.NotFound)
        When("Invoking the DataSurce") {
            val result = sut.invoke()
            Then("It returns a DataNot Found Failure") {
                result shouldBe Either.Left(DataNotFound)
            }
        }
    }
})