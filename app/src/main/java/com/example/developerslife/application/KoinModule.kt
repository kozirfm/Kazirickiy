package com.example.developerslife.application

import com.example.developerslife.model.datasource.RemoteDataSource
import com.example.developerslife.model.datasource.RemoteDataSourceImplementation
import com.example.developerslife.model.repository.Repository
import com.example.developerslife.model.repository.RepositoryImplementation
import com.example.developerslife.model.retrofit.RetrofitApi
import com.example.developerslife.ui.viewmodels.HotPostsViewModel
import com.example.developerslife.ui.viewmodels.LatestPostsViewModel
import com.example.developerslife.ui.viewmodels.TopPostsViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalSerializationApi
val networkModule = module {
    single { RetrofitApi().requestService() }
    single<RemoteDataSource> { RemoteDataSourceImplementation(get()) }
    single<Repository> { RepositoryImplementation(get()) }
}

val viewModelModule = module {
    viewModel { LatestPostsViewModel(get(), get()) }
    viewModel { TopPostsViewModel(get(), get()) }
    viewModel { HotPostsViewModel(get(), get()) }
}