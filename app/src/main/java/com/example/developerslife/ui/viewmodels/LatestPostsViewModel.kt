package com.example.developerslife.ui.viewmodels

import android.content.Context
import com.example.developerslife.model.repository.Repository

class LatestPostsViewModel(context: Context, repository: Repository) :
    BaseViewModel(context, repository) {
    override val selection = "latest"
}