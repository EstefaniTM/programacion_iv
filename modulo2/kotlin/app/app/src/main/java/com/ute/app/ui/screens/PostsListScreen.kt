package com.ute.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ute.app.data.model.PostDTO
import com.ute.app.data.repo.PostsRepository
import kotlinx.coroutines.launch

@Composable
fun PostsListScreen(openDetail: (Int) -> Unit) {
    val repo = remember { PostsRepository() }
    var items by remember { mutableStateOf<List<PostDTO>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            runCatching { repo.fetchPosts() }
                .onSuccess { items = it; loading = false }
                .onFailure { error = it.message; loading = false }
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("📝 Posts (API)", style = MaterialTheme.typography.titleLarge)

        if (loading) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { CircularProgressIndicator() }
        } else if (error != null) {
            Text("Error: $error", color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
                items(items, key = { it.id ?: -1 }) { post ->
                    Card(Modifier.fillMaxWidth().clickable { post.id?.let(openDetail) }) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(post.title, style = MaterialTheme.typography.titleMedium)
                            Text(post.body, maxLines = 2)
                        }
                    }
                }
            }
        }
    }
}