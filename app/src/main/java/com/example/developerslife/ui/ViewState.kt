package com.example.developerslife.ui

sealed class ViewState
class Data<T>(val data: T) : ViewState()
class Error<T>(val message: T) : ViewState()
object Loading : ViewState()
