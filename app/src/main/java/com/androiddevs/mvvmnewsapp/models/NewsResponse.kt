package com.androiddevs.mvvmnewsapp.models


import com.androiddevs.mvvmnewsapp.models.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)