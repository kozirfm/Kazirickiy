package com.example.developerslife.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.developerslife.R
import com.example.developerslife.data.entity.Post
import com.example.developerslife.ui.Data
import com.example.developerslife.ui.Error
import com.example.developerslife.ui.Loading
import com.example.developerslife.ui.viewmodels.TopPostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopPostsFragment : BaseFragment(R.layout.top_posts_fragment_layout) {

    private val topPostsViewModel by viewModel<TopPostsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (topPostsViewModel.postCount == 0) {
            disabledButton(previousPostButton)
            disabledButton(nextPostButton)
        }

        nextPostButton.setOnClickListener {
            topPostsViewModel.nextPost()
            enabledButton(previousPostButton, R.color.orange)
        }

        previousPostButton.setOnClickListener {
            if (topPostsViewModel.prevPost() == 0) {
                disabledButton(previousPostButton)
            }
        }

        repeatButton.setOnClickListener {
            topPostsViewModel.requestPage()
        }

        topPostsViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Loading -> startLoading()
                is Data<*> -> {
                    val data = viewState.data as Post
                    loadImage(data.gifURL)
                    descriptionTextView.text = data.description
                    enabledButton(nextPostButton, R.color.green)
                    stopLoading()
                }
                is Error<*> -> {
                    stopLoading()
                    when (viewState.message) {
                        is Throwable -> showErrorConnection(getString(R.string.connection_error))
                    }
                }
            }
        }
    }
}