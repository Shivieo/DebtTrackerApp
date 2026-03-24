package com.example.debttrackerapp.news

// The root response from the API
data class NewsResponse(
    val status: String,
    val code: String? = null,
    val message: String? = null,
    val totalResults: Int,
    val articles: List<Article>
)

// Individual Article details
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

// The source of the news
data class Source(
    val id: String?,
    val name: String
)

