package com.sisnovate.newsapp.network.model.news

data class NewsModelItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)