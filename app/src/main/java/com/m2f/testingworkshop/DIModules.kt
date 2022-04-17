package com.m2f.testingworkshop

import com.m2f.testingworkshop.presentation.feature.PostsViewModel
import com.picsart.business.di.Dev
import com.picsart.business.di.Environment
import com.picsart.business.di.Module
import com.picsart.business.di.moduleFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val environment = module {
    single { Dev as Environment }
}

val businessModule = module {
    single { moduleFactory().create(get()) }
}

val posts = module {
    viewModel {
        val businessModule: Module = get()
        PostsViewModel(getPostsUsecase = businessModule.postsUseCase)
    }
}
