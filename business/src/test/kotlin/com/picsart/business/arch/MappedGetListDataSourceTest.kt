package com.picsart.business.arch

import arrow.core.Either
import com.picsart.business.arch.mapping.Mapper
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.forAll
import io.mockk.coEvery
import io.mockk.mockk

class MappedGetListDataSourceTest : FunSpec({

    val identityMapper = Mapper<Int, Int> { it }

    val datasource: GetListDataSource<Int> = mockk(relaxed = true)

    test("MappedGetListDataSourceTest does not perform any side effect") {
        Arb.int().forAll { value ->
            val responseList = listOf(value)
            coEvery { datasource.invoke() } returns Either.Right(responseList)
            //map is an extension function that generates a MappedGetListDataSourceTest
            datasource.map(identityMapper).invoke() == datasource()
        }
    }

})
