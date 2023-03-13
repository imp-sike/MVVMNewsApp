package com.sisnovate.newsapp.network.api

import com.sisnovate.newsapp.network.model.news.NewsModelItem
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("posts")
    suspend fun getAllNews(): Response<List<NewsModelItem>>
}