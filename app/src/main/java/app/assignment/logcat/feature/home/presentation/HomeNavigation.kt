package app.assignment.logcat.feature.home.presentation

import androidx.navigation.NavGraphBuilder
import app.assignment.logcat.animatedComposable
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreen(
    onNewsClick: (String) -> Unit
) {
    animatedComposable<HomeRoute> {
        HomeRoute(onNewsClick = onNewsClick)
    }
}

