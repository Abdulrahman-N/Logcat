package app.assignment.logcat.feature.home.domain

import app.assignment.logcat.data.NewsFeedDto
import app.assignment.logcat.formatDate

data class NewsFeedItem(
    val id: String,
    val title: String,
    val publishDate: String,
    val type: String
)

fun NewsFeedDto.toNewsFeedItem(): List<NewsFeedItem> {
    return this.map {
        NewsFeedItem(
            id = it.id,
            title = it.title,
            publishDate = it.publishDate.formatDate(),
            type = it.type
        )
    }

}