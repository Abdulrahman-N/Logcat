package app.assignment.logcat.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.assignment.logcat.openInCustomTab

@Composable
fun UrlLink(
    label: String = "Read More",
    url: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                context.openInCustomTab(url)
            }
            .padding(4.dp)
    )
}