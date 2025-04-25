package app.assignment.logcat.feature.home.presentation

import app.assignment.logcat.feature.home.domain.NewsFeedItem

data class HomeUiState(
    val loading: Boolean = false,
    val news: List<NewsFeedItem> = emptyList(),
    val types: Set<String> = emptySet(),
    val selectedType: String = "All",
    val navigateToNewsDetails: String? = null
)