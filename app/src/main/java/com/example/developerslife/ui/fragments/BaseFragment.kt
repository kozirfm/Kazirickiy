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
import com.google.android.material.floatingactionbutton.FloatingActionButton


abstract class BaseFragment(fragmentLayout: Int) : Fragment(fragmentLayout) {

    private lateinit var imageTextLinearLayout: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var errorConnectionLinearLayout: LinearLayout
    private lateinit var gifImageView: ImageView
    lateinit var descriptionTextView: TextView
    lateinit var nextPostButton: FloatingActionButton
    lateinit var previousPostButton: FloatingActionButton
    lateinit var repeatButton: Button
    private lateinit var errorTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)


    }

    fun startLoading() {
        imageTextLinearLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        errorConnectionLinearLayout.visibility = View.GONE
    }

    fun stopLoading() {
        imageTextLinearLayout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorConnectionLinearLayout.visibility = View.GONE
    }

    fun showErrorConnection(text: String) {
        imageTextLinearLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorConnectionLinearLayout.visibility = View.VISIBLE
        errorTextView.text = text
    }

    fun enabledButton(button: FloatingActionButton, color: Int) {
        button.isEnabled = true
        button.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireContext(),
                color
            )
        )
    }

    fun loadImage(link: String) {
        Glide.with(this)
            .load(link)
            .apply(RequestOptions().transform(RoundedCorners(16)))
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(gifImageView)
    }

    fun disabledButton(button: FloatingActionButton) {
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