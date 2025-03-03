package com.lexanovichok.predictor.news

import retrofit2.Call
import retrofit2.Response

interface NewsRepository {

    suspend fun loadNewsByName() : Response<NewsResponse>

    class Base(private val service: NewsService) : NewsRepository {

        override suspend fun loadNewsByName(): Response<NewsResponse> {
            return service.loadNewsByName()
        }

    }
}