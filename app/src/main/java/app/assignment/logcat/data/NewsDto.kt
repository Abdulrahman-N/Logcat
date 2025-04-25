package app.assignment.logcat.data

import kotlinx.serialization.Serializable

typealias NewsFeedDto = List<NewsItemDto>

@Serializable
data class NewsItemDto(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String,
    val publishDate: String,
    val type: String,
    val topics: List<String>,
    val authors: List<String>
)