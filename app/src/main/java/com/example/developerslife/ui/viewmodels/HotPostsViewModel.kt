package com.example.developerslife.ui.viewmodels

import android.content.Context
import com.example.developerslife.model.repository.Repository

class HotPostsViewModel(context: Context, repository: Repository) :
    BaseViewModel(context, repository) {
    override val selection: String = "hot"
}