package com.lexanovichok.predictor.news

import retrofit2.Call
import retrofit2.Response

interface NewsRepository {

    suspend fun loadNewsByName(name: String) : Response<NewsResponse>

    class Base(private val service: NewsService) : NewsRepository {

        override suspend fun loadNewsByName(name: String): Response<NewsResponse> {
            return service.loadNewsByName(name)
        }

    }
}