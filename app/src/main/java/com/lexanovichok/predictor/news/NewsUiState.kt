package com.lexanovichok.predictor.news

import android.util.Log
import android.view.View
import com.lexanovichok.predictor.databinding.ActivityMainBinding

interface NewsUiState {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val newsText: String,
        private val loadVisibility: Boolean,
        private val progressVisibility: Boolean
    ) : NewsUiState {

        override fun update(binding: ActivityMainBinding) {

        }
    }

    object Initial : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.newsTV.visibility = View.INVISIBLE
            binding.loadButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    data class Error(
        private val errorText : String
    ) : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.newsTV.text = errorText
            binding.newsTV.visibility = View.VISIBLE
            binding.loadButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    object Loading : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            binding.newsTV.visibility = View.INVISIBLE
            binding.loadButton.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    data class Success (
        private val articles : List<Articles>?
    ) : NewsUiState {
        override fun update(binding: ActivityMainBinding) {
            Log.d("API", "text = ${articles?.get(0)?.content}")
            binding.newsTV.text = articles?.get(0)?.content ?: "Some retrofit2 error while loading"
            binding.newsTV.visibility = View.VISIBLE
            binding.loadButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}