package com.ute.app.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AppTopBar(title: String, actions: @Composable () -> Unit = {}) {
    TopAppBar(
        title = { Text(title) },
        actions = { actions() }
    )
}