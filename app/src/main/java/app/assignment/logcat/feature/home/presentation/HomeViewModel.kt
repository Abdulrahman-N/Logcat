package app.assignment.logcat.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.assignment.logcat.feature.home.domain.GetNewsFeedUseCase
import app.assignment.logcat.feature.home.domain.NewsFeedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getNewsFeedUseCase: GetNewsFeedUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    var allNews: List<NewsFeedItem> = emptyList()

    init {
        getNewsFeed()
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnNewsItemClick -> navigateToNewsDetails(event.id)
            is HomeEvents.OnTypeSelected -> updateSelectedType(event.type)
            HomeEvents.NavigateToNewsDetailsHandled -> navigateToNewsDetailsHandled()
        }
    }

    private fun updateSelectedType(type: String) {
        _uiState.update { state ->
            state.copy(
                selectedType = type,
                news = if (type == "All") allNews else allNews.filter { it.type == type }
            )
        }
    }

    private fun getNewsFeed() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(loading = true)
            }
            // Simulate loading
            kotlinx.coroutines.delay(3000)
            allNews = getNewsFeedUseCase()
            _uiState.update { state ->
                state.copy(
                    loading = false,
                    news = allNews,
                    types = setOf("All") + allNews.map { it.type }.distinct()
                )
            }
        }
    }

    private fun navigateToNewsDetailsHandled() {
        _uiState.update { state ->
            state.copy(navigateToNewsDetails = null)
        }
    }

    private fun navigateToNewsDetails(id: String) {
        _uiState.update { state ->
            state.copy(navigateToNewsDetails = id)
        }
    }

}