package com.lexanovichok.predictor

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.lexanovichok.predictor.core.ProvideViewModel
import com.lexanovichok.predictor.databinding.ActivityMainBinding
import com.lexanovichok.predictor.news.NewsViewModel

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = viewModel(NewsViewModel::class.java)

        binding.loadButton.setOnClickListener {
            viewModel.loadNewsByKeyWord()
        }

        viewModel.newsLiveData().observe(this) { uiState ->
            uiState.update(binding)
        }
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as ProvideViewModel).viewModel(viewModelClass)
    }
}