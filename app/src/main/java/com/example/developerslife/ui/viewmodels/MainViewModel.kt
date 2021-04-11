package com.example.developerslife.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developerslife.R
import com.example.developerslife.data.entity.Post
import com.example.developerslife.model.repository.Repository
import com.example.developerslife.ui.Data
import com.example.developerslife.ui.Error
import com.example.developerslife.ui.Loading
import com.example.developerslife.ui.ViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application, private val selection: String, private val repository: Repository) :
    ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    private val listResult = ArrayList<Post>()

    var postCount = 0
    private var page = 0

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewState.value = Error(throwable)
    }

    init {
        requestPage(page)
    }

    fun requestPage(page: Int = this.page + 1) {
        viewModelScope.launch(exceptionHandler) {
            viewState.value = Loading
            val result = repository.getPosts(selection, page).result
            if (result.count() > 0) {
                if (!listResult.containsAll(result)) {
                    listResult.addAll(result)
                }
                viewState.value = Data(listResult[postCount])
            } else {
                viewState.value = Error(application.getString(R.string.empty_list))
            }
        }
    }

    fun nextPost() {
        if (postCount < listResult.lastIndex) {
            postCount++
            viewState.value = Data(listResult[postCount])
        } else {
            page++
            postCount++
            requestPage(page)
        }
    }

    fun prevPost(): Int {
        if (postCount > 0) {
            postCount--
            viewState.value = Data(listResult[postCount])
        }
        return postCount
    }

    override fun onCleared() {
        exceptionHandler.cancel()
        super.onCleared()
    }

}