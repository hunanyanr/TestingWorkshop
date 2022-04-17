package com.picsart.business.arch

import arrow.core.Either
import com.picsart.business.arch.mapping.Mapper
import com.picsart.business.arch.mapping.biject
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.forAll
import io.mockk.coEvery
import io.mockk.mockk

class BijectedDataSourceTest : FunSpec({

    val identityMapper = Mapper<Int, Int> { it }
    val identityBijection = identityMapper.biject()

    val storeDataSource: StoreDataSource<Int> = mockk(relaxed = true)

    test("BijectedDataSource does not perform any side effect on it's get") {
        Arb.int().forAll { value ->
            val responseList = listOf(value)
            coEvery { storeDataSource.invoke() } returns Either.Right(responseList)
            //biject is a extension function that creates a BijectedDataSource
            storeDataSource.biject(identityBijection)() == storeDataSource()
        }
    }

    test("BijectedDataSource does not perform any side effect on it's put") {
        Arb.int().forAll { value ->
            val responseList = listOf(value)
            coEvery { storeDataSource.invoke(any()) } answers { Either.Right(arg(0)) }
            //biject is a extension function that creates a BijectedDataSource
            storeDataSource.biject(identityBijection).invoke(responseList) == storeDataSource(
                responseList
            )
        }
    }
})
