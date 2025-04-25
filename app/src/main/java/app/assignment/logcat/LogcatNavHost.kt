package app.assignment.logcat

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.assignment.logcat.feature.details.presentation.navigateToNewsDetails
import app.assignment.logcat.feature.details.presentation.newsDetailsScreen
import app.assignment.logcat.feature.home.presentation.HomeRoute
import app.assignment.logcat.feature.home.presentation.homeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun LogcatNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(
                navController = navController,
                startDestination = HomeRoute,
                modifier = modifier
            ) {
                homeScreen(
                    onNewsClick = { id ->
                        navController.navigateToNewsDetails(id)
                    }
                )
                newsDetailsScreen(navController::navigateUp)
            }
        }
    }
}