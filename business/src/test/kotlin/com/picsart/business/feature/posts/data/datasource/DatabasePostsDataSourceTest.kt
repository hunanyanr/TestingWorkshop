package com.picsart.business.feature.posts.data.datasource

import io.kotest.core.spec.style.BehaviorSpec

class DatabasePostsDataSourceTest : BehaviorSpec({

    /*Test cases:
    * - it returns either Result.success or Result.failure
    * - if there is no stored data it returns a DataEmpty Failure
    * - if there is stored data then the data source returns it
    * - DataSource does not persist data over recreation */

})
