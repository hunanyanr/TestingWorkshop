package com.picsart.business.feature.posts.data.datasource

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NetworkGetPostsDataSourceTest : BehaviorSpec({

    /*Test cases:
    * - it returns either left or right
    * - if there is no network problem the data source returns the httpclient response body
    * - if there is a server error (code 5**) the data source returns a ServerError Failure */
})
