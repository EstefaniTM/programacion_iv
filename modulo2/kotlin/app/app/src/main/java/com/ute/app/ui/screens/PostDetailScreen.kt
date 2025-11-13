package com.ute.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ute.app.data.model.PostDTO
import com.ute.app.data.repo.PostsRepository
import kotlinx.coroutines.launch

@Composable
fun PostDetailScreen(id: Int) {
    val repo = remember { PostsRepository() }
    var post by remember { mutableStateOf<PostDTO?>(null) }
    var error by remember { mutableStateOf(null) }
    var loading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(id) {
        scope.launch {
            runCatching { repo.fetchPost(id) }
                .onSuccess { post = it; loading = false }
                .onFailure { error = it.message; loading = false }
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("ℹ️ Detalle Post #$id", style = MaterialTheme.typography.titleLarge)

        when {
            loading -> CircularProgressIndicator()
            error != null -> Text("Error: $error", color = MaterialTheme.colorScheme.error)
            post == null -> Text("No encontrado")
            else -> {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(post!!.title, style = MaterialTheme.typography.titleMedium)
                        Text(post!!.body)
                    }
                }
            }
        }
    }
}