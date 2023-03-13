package com.sisnovate.newsapp.repository

import com.sisnovate.newsapp.network.api.NewsApi
import com.sisnovate.newsapp.network.model.news.NewsModelItem
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private var newsApi: NewsApi) {

    suspend fun getAllNews(): Response<List<NewsModelItem>> {
        return newsApi.getAllNews()
    }

}