package com.example.todobyKotlin.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun TODObyKotlinTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}
