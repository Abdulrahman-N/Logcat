package app.assignment.logcat.feature.details.presentation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.assignment.logcat.LocalAnimatedContentScope
import app.assignment.logcat.LocalSharedTransitionScope
import app.assignment.logcat.ui.components.IconButton
import app.assignment.logcat.ui.components.Image
import app.assignment.logcat.ui.components.UrlLink

@Composable
fun NewsDetailsRoute(
    viewModel: NewsDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewsDetailsScreen(
        uiState = uiState,
        onBackClick = onBackClick
    )

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun NewsDetailsScreen(
    uiState: NewsDetailsUiState,
    onBackClick: () -> Unit
) {

    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedContentScope = LocalAnimatedContentScope.current

    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "item-${uiState.data.id}"),
                    animatedVisibilityScope = animatedContentScope,
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    url = uiState.data.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                IconButton(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .statusBarsPadding(),
                    icon = app.assignment.logcat.R.drawable.ic_back,
                    onClick = onBackClick
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${uiState.data.type}  |  ${uiState.data.publishDate}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = uiState.data.title,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 26.sp
            )
            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
            ) {
                Text(
                    text = uiState.data.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(Modifier.height(8.dp))
                UrlLink(
                    url = uiState.data.url
                )
            }
            Spacer(Modifier.height(16.dp))

        }
    }
}