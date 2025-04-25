package app.assignment.logcat.data

interface NewsDataSource {
    suspend fun getNews(): NewsFeedDto
    suspend fun getNewsById(id: String): NewsItemDto?
}