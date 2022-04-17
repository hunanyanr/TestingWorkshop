package com.m2f.testingworkshop.presentation.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.m2f.testingworkshop.presentation.utils.Idle
import com.m2f.testingworkshop.presentation.utils.State
import com.picsart.business.arch.GetListUseCase
import com.picsart.business.feature.posts.model.Post
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Tests: PostsViewModelTest.kt
 */
class PostsViewModel(private val getPostsUsecase: GetListUseCase<Post>) :
    ViewModel() {

    private val _state: MutableStateFlow<State<List<Post>>> = MutableStateFlow(Idle)

    fun posts(): LiveData<State<List<Post>>> = TODO()
}