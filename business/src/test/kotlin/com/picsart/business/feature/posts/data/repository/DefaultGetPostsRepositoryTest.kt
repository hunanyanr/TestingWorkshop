package com.picsart.business.feature.posts.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.picsart.business.arch.GetListDataSource
import com.picsart.business.arch.StoreDataSource
import com.picsart.business.arch.error.*
import com.picsart.business.feature.posts.model.Post
import com.picsart.business.generators.post
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next
import io.mockk.*

class DefaultGetPostsRepositoryTest : BehaviorSpec({

    val networkDataSource: GetListDataSource<Post> = mockk(relaxed = true)
    val storeDataSource: StoreDataSource<Post> = mockk(relaxed = true)

    xGiven("DefaultGetPostsRepository") {
        val sut = DefaultGetPostsRepository(
            networkDataSource,
            storeDataSource
        )
        xWhen("The Network data source successfully returns posts") {
            val resultList = Arb.list(Arb.post()).next()
            coEvery { networkDataSource.invoke() } returns resultList.right()

            //"answer" is a cool tool that lets you get the arguments taken in the body of the "coEvery"
            coEvery { storeDataSource.invoke(any()) } answers { Either.Right(arg(0)) }
            xAnd("Repository is invoked") {
                val result = sut.invoke()
                /*this may sound confusing but as long as Either object can only be Right or Left
                 * it make sense to check that we are under the scope of it (perhaps throwing an
                 * exception may not be under the scope of Left and Right */
                xThen("It returns either Right or Left") {
                    assert(result.isLeft() || result.isRight())
                }
                xThen("Repository returns the network result") {
                    coVerify(exactly = 1) {
                        networkDataSource.invoke()
                        storeDataSource.invoke() wasNot Called
                    }
                    result shouldBe resultList.right()
                }
                xThen("Repository stores the result data into the cache") {
                    coVerify { storeDataSource.invoke(resultList) }
                }

            }
        }

        xWhen("The Network data source fails for any reason") {
            val resultList = Arb.list(Arb.post()).next()
            coEvery { networkDataSource.invoke() } returns ServerError.left()
            coEvery { storeDataSource.invoke() } returns resultList.right()
            xAnd("Repository is invoked") {
                val result = sut.invoke()
                xThen("It returns either Right or Left") {
                    assert(result.isLeft() || result.isRight())
                }
                xThen("Repository fetches the data from cache") {
                    coVerify(exactly = 1) { storeDataSource() }
                    result shouldBe resultList.right()
                }
            }
        }
    }
})
