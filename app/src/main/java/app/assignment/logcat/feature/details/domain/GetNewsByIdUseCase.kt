package app.assignment.logcat.feature.details.domain

import app.assignment.logcat.data.NewsDataSource
import javax.inject.Inject

class GetNewsByIdUseCase @Inject constructor(private val datasource: NewsDataSource) {

    suspend operator fun invoke(id: String) = datasource.getNewsById(id)?.toNewsDetailsItem()

}