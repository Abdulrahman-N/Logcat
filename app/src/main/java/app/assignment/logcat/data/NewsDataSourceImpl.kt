package app.assignment.logcat.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(@ApplicationContext private val context: Context, private val json: Json) : NewsDataSource {

    override suspend fun getNews(): NewsFeedDto = withContext(Dispatchers.IO) {
        val newsFeedJson = context.assets.open("newsfeed.json")
            .bufferedReader()
            .use { it.readText() }

        json.decodeFromString(newsFeedJson)
    }

    override suspend fun getNewsById(id: String): NewsItemDto? = withContext(Dispatchers.IO) {
        val newsFeedJson = context.assets.open("newsfeed.json")
            .bufferedReader()
            .use { it.readText() }

        json.decodeFromString<NewsFeedDto>(newsFeedJson).find { it.id == id }
    }

}