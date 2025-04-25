package app.assignment.logcat.feature.home.presentation

sealed interface HomeEvents {
    data class OnNewsItemClick(val id: String) : HomeEvents
    data class OnTypeSelected(val type: String) : HomeEvents
    data object NavigateToNewsDetailsHandled : HomeEvents
}