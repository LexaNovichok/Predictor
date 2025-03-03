package com.lexanovichok.predictor.news

import android.util.Log
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("get2")
    suspend fun loadNewsByName(): Response<NewsResponse>


    object Base : NewsService {

        private const val BASE_URL : String = "http://194.116.217.63:8008/api/news/"
        //private const val API_KEY = "17e2b975fb4444bf8f73c055bf67ee95"

        override suspend fun loadNewsByName(): Response<NewsResponse> {

//            val interceptor = Interceptor { chain ->
//                val original = chain.request()
//                val originalUrl = original.url
//
//                val newUrl = originalUrl.newBuilder()
//                    .addQueryParameter("apiKey", API_KEY)
//                    .build()
//
//                val newRequest = original.newBuilder()
//                    .url(newUrl)
//                    .build()
//
//                chain.proceed(newRequest)
//            }
//
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service : NewsService = retrofit.create(NewsService::class.java)
            val actual = service.loadNewsByName()
            return actual
        }

    }
}

data class NewsResponse(

    @SerializedName("newsreal")
    val newsReal: String,

    @SerializedName("newsfake")
    val newsFake: String,

)

//data class NewsResponse(
//
//    @SerializedName("status")
//    val status: String,
//
//    @SerializedName("totalResult")
//    val resultCode: Int,
//
//    @SerializedName("articles")
//    val articles: List<Articles>
//)
//
//data class Articles(
//    @SerializedName("title")
//    val title: String,
//
//    @SerializedName("description")
//    val description: String,
//
//    @SerializedName("url")
//    val url: String,
//
//    @SerializedName("publishedAt")
//    val date: String,
//
//    @SerializedName("content")
//    val content: String
//)