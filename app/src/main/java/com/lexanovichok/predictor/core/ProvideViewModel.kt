package com.lexanovichok.predictor.core

import androidx.lifecycle.ViewModel
import com.lexanovichok.predictor.news.NewsService
import com.lexanovichok.predictor.news.NewsViewModel
import com.lexanovichok.predictor.news.NewsRepository
import com.lexanovichok.predictor.news.NewsUiStateLiveDataWrapper

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>) : T

    class Base(
        private val clearViewModel : ClearViewModel
    ) : ProvideViewModel {

        private val newsUiStateLiveDataWrapper = NewsUiStateLiveDataWrapper.Base()
        private val newsService = NewsService.Base
        private val newsRepo = NewsRepository.Base(newsService)

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when(viewModelClass) {

                NewsViewModel::class.java -> NewsViewModel(newsRepo = newsRepo ,newsUiState = newsUiStateLiveDataWrapper)
                else -> throw IllegalStateException("unknown viewModelClass $viewModelClass")
            } as T
        }

    }
}