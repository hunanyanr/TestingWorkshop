package com.picsart.business.feature.posts.data.datasource

import arrow.core.Either
import com.picsart.business.arch.error.Empty
import com.picsart.business.generators.dboPost
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next

class DatabasePostsDataSourceTest : BehaviorSpec({

    /*Test cases:
    * - if there is no stored data it returns a DataEmpty Failure
    * - if there is stored data then the data source returns it
    * - It can store data*/


    Given("DatabasePostsDataSource") {
        val sut = DatabasePostsDataSource()
        When("storing") {
            val posts = Arb.list(Arb.dboPost()).next()
            val result = sut(posts)
            Then("It returns the last saved value") {
                result shouldBe sut()
            }
        }
        When("Getting the value") {
            And("The storage is empty") {
                sut(emptyList())
                val result = sut()
                Then("The result is an Empty Failure") {
                    result shouldBe Either.Left(Empty)
                }
            }
            And("The storage is not empty") {
                val posts = Arb.list(Arb.dboPost()).next()
                sut(posts)
                val result = sut()
                Then("The result is the expected list of posts") {
                    result shouldBe Either.Right(posts)
                }
            }
        }
    }
})
