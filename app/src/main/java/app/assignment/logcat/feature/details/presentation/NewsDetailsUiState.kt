package app.assignment.logcat.feature.details.presentation

import app.assignment.logcat.feature.details.domain.NewsDetailsItem

data class NewsDetailsUiState(
    val loading: Boolean = false,
    val data: NewsDetailsItem = NewsDetailsItem()
)