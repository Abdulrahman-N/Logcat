package app.assignment.logcat

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.net.toUri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

fun String.formatDate(): String {
    val instant = Instant.parse(this)
    val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${localDate.date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())} ${localDate.date.dayOfMonth}, ${localDate.date.year}"
}

fun String.removeEmojis(): String {
    val emojiRegex = Regex("[\\p{So}\\p{Cn}]|[\uD83C-\uDBFF\uDC00-\uDFFF]+")
    return emojiRegex.replace(this, "").replace(" ", "")

}

fun Context.openInCustomTab(url: String) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .setCloseButtonPosition(CustomTabsIntent.CLOSE_BUTTON_POSITION_END)
        .setShowTitle(true)
        .setUrlBarHidingEnabled(false)
        .build()
    customTabsIntent.launchUrl(this, url.toUri())
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    noinline content: @Composable (NavBackStackEntry) -> Unit
) {
    composable<T> {
        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
            content(it)
        }
    }

}