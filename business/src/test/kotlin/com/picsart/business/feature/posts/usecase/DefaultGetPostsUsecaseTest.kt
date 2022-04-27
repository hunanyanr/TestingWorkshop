package com.picsart.business.feature.posts.usecase

import arrow.core.left
import arrow.core.right
import com.picsart.business.arch.GetListRepository
import com.picsart.business.arch.error.Empty
import com.picsart.business.feature.posts.model.Post
import com.picsart.business.generators.post
import io.kotest.assertions.fail
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class DefaultGetPostsUsecaseTest() : BehaviorSpec({

    /* Test cases:
    *  - It returns either left or right
    *  - It propagates the result of the repository
    *  - It propagates the failure of the repository
    *  - It filters posts without title
    * */

    Given("DefaultGetPostsUsecase") {
        val getPostsRepository: GetListRepository<Post> = mockk(relaxed = true)
        val posts = Arb.list(Arb.post(), 1..10).next()
        val sut = DefaultGetPostsUsecase(getPostsRepository)
        When("The repository returns a valid response") {
            coEvery { getPostsRepository() } returns posts.right()
            And("We invoke the use case") {
                val result = sut()
                Then("It returns Right") {
                    assert(result.isRight())
                }
                Then("It propagates the result of the repository") {
                    coVerify(exactly = 1) { getPostsRepository() }
                }
                Then("It should filter posts without title") {
                    result.fold(
                        ifLeft = { fail("This case shouldn't happen") },
                        ifRight = { it.forAll { it.title.isNotBlank() } }
                    )
                }
            }
        }
        When("The repository returns an error") {
            val error = Empty
            coEvery { getPostsRepository() } returns error.left()
            And("The use case is invoked") {
                val result = sut()
                Then("It returns Left") {
                    assert(result.isLeft())
                }
                Then("It propagates the error from the repository") {
                    coVerify { getPostsRepository() }
                    result shouldBe error.left()
                }
            }
        }
    }

})
