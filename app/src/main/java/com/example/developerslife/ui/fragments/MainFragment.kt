package com.example.developerslife.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.developerslife.R
import com.example.developerslife.data.entity.Post
import com.example.developerslife.ui.Data
import com.example.developerslife.ui.Error
import com.example.developerslife.ui.Loading
import com.example.developerslife.ui.viewmodels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment(R.layout.card_layout) {

    private val mainViewModel by viewModel<MainViewModel> { parametersOf(this.arguments?.getString("key")) }

    private lateinit var imageTextLinearLayout: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var errorConnectionLinearLayout: LinearLayout
    private lateinit var gifImageView: ImageView
    private lateinit var descriptionTextView: TextView
    private lateinit var nextPostButton: FloatingActionButton
    private lateinit var previousPostButton: FloatingActionButton
    private lateinit var repeatButton: Button
    private lateinit var errorTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        if (mainViewModel.postCount == 0) {
            disableButton(previousPostButton)
            disableButton(nextPostButton)
        }

        nextPostButton.setOnClickListener {
            mainViewModel.nextPost()
            enableButton(previousPostButton, R.color.orange)
        }

        previousPostButton.setOnClickListener {
            if (mainViewModel.prevPost() == 0) {
                disableButton(previousPostButton)
            }
        }

        repeatButton.setOnClickListener {
            mainViewModel.requestPage()
        }

        mainViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Loading -> startLoading()
                is Data<*> -> {
                    val data = viewState.data as Post
                    loadImage(data.gifURL)
                    descriptionTextView.text = data.description
                    enableButton(nextPostButton, R.color.green)
                    stopLoading()
                }
                is Error<*> -> {
                    stopLoading()
                    when (viewState.message) {
                        is String -> showErrorConnection(viewState.message)
                        is Throwable -> showErrorConnection(getString(R.string.connection_error))
                    }
                }
            }
        }
    }

    private fun startLoading() {
        imageTextLinearLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        errorConnectionLinearLayout.visibility = View.GONE
    }

    private fun stopLoading() {
        imageTextLinearLayout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorConnectionLinearLayout.visibility = View.GONE
    }

    private fun showErrorConnection(text: String) {
        imageTextLinearLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorConnectionLinearLayout.visibility = View.VISIBLE
        errorTextView.text = text
    }

    private fun enableButton(button: FloatingActionButton, color: Int) {
        button.isEnabled = true
        button.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                color
            )
        )
    }

    private fun loadImage(link: String) {
        Glide.with(this)
            .load(link)
            .apply(RequestOptions().transform(RoundedCorners(16)))
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(gifImageView)
    }

    private fun disableButton(button: FloatingActionButton) {
        button.isEnabled = false
        button.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.darker_gray
            )
        )
    }

    private fun initView(view: View) {
        gifImageView = view.findViewById(R.id.gifImageView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        previousPostButton = view.findViewById(R.id.previousButton)
        nextPostButton = view.findViewById(R.id.nextButton)
        repeatButton = view.findViewById(R.id.repeatButton)
        imageTextLinearLayout = view.findViewById(R.id.imageTextLinearLayout)
        progressBar = view.findViewById(R.id.progressBar)
        errorConnectionLinearLayout = view.findViewById(R.id.errorConnectionLinearLayout)
        errorTextView = view.findViewById(R.id.errorText)
    }

}