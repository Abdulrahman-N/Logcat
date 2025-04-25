package app.assignment.logcat.feature.home.domain

import app.assignment.logcat.data.NewsDataSource
import javax.inject.Inject

class GetNewsFeedUseCase @Inject constructor(
    private val datasource: NewsDataSource
) {
    suspend operator fun invoke(): List<NewsFeedItem> {
        return datasource.getNews().toNewsFeedItem()
    }
}