package app.assignment.logcat.feature.details.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import app.assignment.logcat.animatedComposable
import kotlinx.serialization.Serializable

@Serializable
data class NewsDetailsRoute(val id: String)

fun NavController.navigateToNewsDetails(
    newsId: String,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = NewsDetailsRoute(newsId)) {
        navOptions()
    }
}

fun NavGraphBuilder.newsDetailsScreen(
    onBackClick: () -> Unit
) {
    animatedComposable<NewsDetailsRoute> {
        NewsDetailsRoute(
            onBackClick = onBackClick
        )
    }
}