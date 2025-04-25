package app.assignment.logcat.feature.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.assignment.logcat.feature.details.domain.GetNewsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val getNewsByIdUseCase: GetNewsByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState = flow {
        emit(NewsDetailsUiState(loading = true))
        val newsItem = getNewsByIdUseCase(savedStateHandle.toRoute<NewsDetailsRoute>().id)
        newsItem?.let {
            emit(NewsDetailsUiState(loading = false, data = newsItem))
        } ?: emit(NewsDetailsUiState(loading = false))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NewsDetailsUiState(loading = true)
    )

}