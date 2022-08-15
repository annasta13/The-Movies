package com.habileducation.themovie.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = Color.Gray,
    primaryVariant = Color.White,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun LightTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = lightColors(
            primary = Color.Black,
            primaryVariant = Color.Black
        ),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun DarkTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = darkColors(),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = Color.Black, //Your color
            darkIcons = isDarkTheme
        )
    }
    return if (isDarkTheme) DarkTheme(content = content)
    else LightTheme(content = content)
}