package com.lexanovichok.predictor.news

import androidx.lifecycle.LiveData
import com.lexanovichok.predictor.core.LiveDataWrapper

interface NewsUiStateLiveDataWrapper {

    interface Read {
        fun liveData() : LiveData<NewsUiState>
    }

    interface Update {
        fun update(value : NewsUiState)
    }

    interface Mutable : Read, Update

    class Base : LiveDataWrapper.Abstract<NewsUiState>(), Mutable
}