package app.assignment.logcat.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF000000),
    secondary = Color(0xFF00ADB5),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFf4f5f9),
    outlineVariant = Color(0xFFE9EAEE),
    onBackground = Color(0xFF000000),
    outline = Color(0xFF838383)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFDF0ED),
    secondary = Color(0xFF00ADB5),
    surface = Color(0xFF1C1E26),
    background = Color(0xFF16161C),
    outlineVariant = Color(0xFF21252D),
    onBackground = Color(0xFFFDF0ED),
    outline = Color(0xFF64676F)
)