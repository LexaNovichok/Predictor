package com.lexanovichok.predictor.news

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepo: NewsRepository,
    private val newsUiState : NewsUiStateLiveDataWrapper.Mutable,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun loadNewsByKeyWord(keyWord : String) {
        newsUiState.update(NewsUiState.Loading)
        viewModelScope.launch {
            try {
                val response = newsRepo.loadNewsByName(keyWord)
                if (response.isSuccessful) {
                    val articles = response.body()?.articles

                    if (!articles.isNullOrEmpty()) {
                        newsUiState.update(NewsUiState.Success(articles))
                    } else {
                        newsUiState.update(NewsUiState.Initial)
                    }
                } else {
                    newsUiState.update(NewsUiState.Error("Ошибка: ${response.code()}"))
                }
            } catch (e: Exception) {
                newsUiState.update(NewsUiState.Error("Ошибка сети: ${e.message}"))
            }
        }
    }

    fun newsLiveData() = newsUiState.liveData()
}