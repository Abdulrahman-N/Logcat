package app.assignment.logcat.feature.home.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.assignment.logcat.LocalAnimatedContentScope
import app.assignment.logcat.LocalSharedTransitionScope
import app.assignment.logcat.ui.components.Chip
import app.assignment.logcat.ui.components.Loading

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewsClick: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateToNewsDetails) {
        uiState.navigateToNewsDetails?.let {
            viewModel.onEvent(HomeEvents.NavigateToNewsDetailsHandled)
            onNewsClick(it)
        }
    }

    HomeScreen(
        uiState = uiState,
        handleEvent = viewModel::onEvent
    )

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    handleEvent: (HomeEvents) -> Unit
) {

    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedContentScope = LocalAnimatedContentScope.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Row(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(40.dp)
                    .rotate(340F),
                painter = painterResource(app.assignment.logcat.R.drawable.ic_logcat),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = "LOGCAT</>",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Crossfade(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            targetState = uiState.loading
        ) { isLoading ->
            if (isLoading) Column(modifier = Modifier.fillMaxWidth()) { Loading(Modifier.align(Alignment.CenterHorizontally)) }
            else Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.types.forEach { type ->
                        Chip(
                            text = type,
                            selected = uiState.selectedType == type,
                            onClick = { handleEvent(HomeEvents.OnTypeSelected(type)) }
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                    )
                ) {
                    itemsIndexed(uiState.news) { index, item ->
                        with(sharedTransitionScope) {
                            NewsItem(
                                modifier = Modifier
                                    .animateItem()
                                    .sharedElement(
                                        sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "item-${item.id}"),
                                        animatedVisibilityScope = animatedContentScope
                                    ),
                                item = item,
                                onClick = { handleEvent(HomeEvents.OnNewsItemClick(item.id)) }
                            )
                        }
                        if (index != uiState.news.lastIndex) HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }
}