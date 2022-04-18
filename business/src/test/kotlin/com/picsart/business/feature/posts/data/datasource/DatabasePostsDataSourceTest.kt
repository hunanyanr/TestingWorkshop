package com.picsart.business.feature.posts.data.datasource

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DatabasePostsDataSourceTest : BehaviorSpec({

    /*Test cases:
    * - it returns either Result.success or Result.failure
    * - if there is no stored data it returns a DataEmpty Failure
    * - if there is stored data then the data source returns it
    *   - data source used a list as a storage mechanism
    *   - write/read operations are thread safe*/

})
