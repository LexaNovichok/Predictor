package com.lexanovichok.predictor.news

import android.util.Log
import android.view.View
import com.lexanovichok.predictor.databinding.ActivityMainBinding

interface NewsUiState {

    fun update(binding: ActivityMainBinding)

    object Initial : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.news1TV.visibility = View.INVISIBLE
            binding.news2TV.visibility = View.INVISIBLE
            binding.loadButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    data class Error(
        private val errorText : String
    ) : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.news1TV.text = errorText
            binding.news1TV.visibility = View.VISIBLE
            binding.news2TV.text = errorText
            binding.news2TV.visibility = View.VISIBLE
            binding.loadButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    object Loading : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.news1TV.visibility = View.INVISIBLE
            binding.news2TV.visibility = View.INVISIBLE
            binding.loadButton.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    data class Success (
        private val articles : NewsResponse?
    ) : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.news1TV.text = articles?.newsReal ?: "Some retrofit2 error while loading"
            binding.news1TV.visibility = View.VISIBLE
            binding.news2TV.text = articles?.newsFake ?: "Some retrofit2 error while loading"
            binding.news2TV.visibility = View.VISIBLE

            binding.loadButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}