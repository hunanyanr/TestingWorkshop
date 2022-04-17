package com.picsart.business.di.dependencies

import io.ktor.client.*

interface NetworkDependencies {
    fun httpClient(): HttpClient
    fun baseEndpoint(): String
}