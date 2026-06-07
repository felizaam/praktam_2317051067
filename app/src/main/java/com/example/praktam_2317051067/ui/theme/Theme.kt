package com.example.praktam_2317051067.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BookColorScheme = lightColorScheme(
    primary = BookPrimary,
    secondary = BookSecondary,
    background = BookBackground,
    surface = BookSurface,
    onPrimary = BookOnPrimary,
    onBackground = BookOnBackground,
    onSurface = BookOnSurface
)

@Composable
fun BookNestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BookColorScheme,
        typography = BookTypography,
        content = content
    )
}