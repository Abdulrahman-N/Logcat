package app.assignment.logcat.feature.details.domain

import app.assignment.logcat.data.NewsItemDto
import app.assignment.logcat.formatDate

data class NewsDetailsItem(
    val id: String = "",
    val title: String = "",
    val publishDate: String = "",
    val type: String = "",
    val content: String = "",
    val url: String = "",
    val image: String = "",
    val authors: List<String> = emptyList(),
    val topics: List<String> = emptyList()
)

fun NewsItemDto.toNewsDetailsItem(): NewsDetailsItem {
    return NewsDetailsItem(
        id = id,
        title = title,
        publishDate = publishDate.formatDate(),
        type = type,
        content = content,
        image = headerImageUrl,
        url = url,
        authors = authors,
        topics = topics
    )
}