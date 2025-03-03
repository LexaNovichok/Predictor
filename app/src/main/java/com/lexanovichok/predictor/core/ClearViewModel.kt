package com.lexanovichok.predictor.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clearViewModel(viewModelClass : Class<out ViewModel>)
}