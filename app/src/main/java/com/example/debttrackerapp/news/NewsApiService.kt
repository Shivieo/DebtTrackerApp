package com.example.debttrackerapp.news

import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    @GET("v2/top-headlines") // fetch data from this folder on server
    // Kotlin coroutine modifier
    suspend fun getStockNews(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
//        @Query("q") query: String = "stock market",
        @Query("apiKey") apiKey: String // security pass
    ): NewsResponse
}