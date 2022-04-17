package com.m2f.testingworkshop.presentation.feature

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PostsViewModelTest : BehaviorSpec({

    /*Test cases:
    * - the viewmodel can load posts that returns a State
    * - loading posts calls a usecase for that purpose
    * - when prompted to load posts the viewmodel emits a Loading state
    * - when the usecase loads posts succesfully then the viwemodel emits a loaded state with the list of posts emited by the usecase
    * - for any error coming frrom the usecase viewmodel emits a DataEmpty Failure error state
    * */

})
