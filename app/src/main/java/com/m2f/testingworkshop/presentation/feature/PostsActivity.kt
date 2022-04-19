package com.m2f.testingworkshop.presentation.feature

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.m2f.testingworkshop.R
import com.m2f.testingworkshop.presentation.utils.render
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : ComponentActivity() {
    companion object {
        const val TAG = "PostsActivity"
    }

    private val viewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        viewModel.posts().render {
            onLoading = { Log.d(TAG, "Loading") }
            onLoaded = { Log.d(TAG, "Loaded ${it.size} items") }
            onError = { Log.d(TAG, "Error") }
        }
    }
}
