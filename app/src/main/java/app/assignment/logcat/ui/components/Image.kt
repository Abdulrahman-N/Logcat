package app.assignment.logcat.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun Image(
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {

    AsyncImage(
        model = url,
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = ""
    )
}