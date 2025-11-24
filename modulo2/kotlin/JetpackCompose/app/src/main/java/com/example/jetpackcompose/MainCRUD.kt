package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.*
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

// ---------- 1) Modelo ----------
@Serializable
data class PostDTO(
    val userId: Int = 1,
    val id: Int? = null,
    val title: String,
    val body: String
)

// ---------- 2) API Retrofit ----------
interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostDTO>

    @POST("posts")
    suspend fun createPost(@Body post: PostDTO): PostDTO

    // (Opcional para más tarde)
    // @PUT("posts/{id}") suspend fun updatePost(@Path("id") id: Int, @Body post: PostDTO): PostDTO
    // @DELETE("posts/{id}") suspend fun deletePost(@Path("id") id: Int)
}

// ---------- 3) Proveedor de Retrofit ----------
object ApiProvider {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val contentType = "application/json".toMediaType()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(client)
        .build()

    val postsApi: PostsApi = retrofit.create(PostsApi::class.java)
}

// ---------- 4) Repositorio ----------
class PostsRepository(private val api: PostsApi) {
    suspend fun fetchPosts(): List<PostDTO> = api.getPosts()
    suspend fun addPost(title: String, body: String): PostDTO =
        api.createPost(PostDTO(title = title, body = body))
}

// ---------- 5) UI State ----------
data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<PostDTO> = emptyList(),
    val error: String? = null
)

// ---------- 6) ViewModel ----------
class PostsViewModel(
    private val repo: PostsRepository = PostsRepository(ApiProvider.postsApi)
) : ViewModel() {

    var uiState by mutableStateOf(PostsUiState(isLoading = true))
        private set

    init { refresh() }

    fun refresh() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val list = repo.fetchPosts().take(15) // limitamos para la demo
                uiState = uiState.copy(isLoading = false, posts = list)
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, error = e.message ?: "Error desconocido")
            }
        }
    }

    fun create(title: String, body: String, onDone: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val created = repo.addPost(title, body)
                // JSONPlaceholder no persiste, pero agregamos localmente para ver el cambio
                uiState = uiState.copy(posts = listOf(created) + uiState.posts)
                onDone()
            } catch (e: Exception) {
                uiState = uiState.copy(error = e.message ?: "No se pudo crear")
            }
        }
    }
}

// ---------- 7) Activity ----------
class MainCRUD : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PostsCrudApp() }
    }
}

// ---------- 8) UI Compose ----------
@Composable
fun PostsCrudApp() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            val vm = remember { PostsViewModel() }
            PostsScreen(vm = vm)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(vm: PostsViewModel) {
    val state = vm.uiState
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var body by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("CRUD básico: Posts") }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // --- Formulario crear ---
            Text("Crear nuevo post", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Contenido") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { title = TextFieldValue(""); body = TextFieldValue("") }) { Text("Limpiar") }
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        val t = title.text.trim()
                        val b = body.text.trim()
                        if (t.isNotEmpty() && b.isNotEmpty()) {
                            vm.create(t, b) { title = TextFieldValue(""); body = TextFieldValue("") }
                        }
                    },
                    enabled = title.text.isNotBlank() && body.text.isNotBlank()
                ) { Text("Crear") }
            }

            HorizontalDivider()

            // --- Estado de carga / error ---
            when {
                state.isLoading -> {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        CircularProgressIndicator()
                    }
                }
                state.error != null -> {
                    Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                    TextButton(onClick = { vm.refresh() }) { Text("Reintentar") }
                }
                else -> {
                    Text("Posts (${state.posts.size})", style = MaterialTheme.typography.titleMedium)
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.posts, key = { it.id ?: -1 }) { post ->
                            Card(Modifier.fillMaxWidth()) {
                                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text(post.title, style = MaterialTheme.typography.titleMedium)
                                    Text(post.body, style = MaterialTheme.typography.bodyMedium)
                                    AssistChipsRow()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AssistChipsRow() {
    // Lugar para futuras acciones: Editar, Eliminar (PUT/DELETE)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        AssistChip(onClick = { /* TODO: editar */ }, label = { Text("Editar") })
        AssistChip(onClick = { /* TODO: eliminar */ }, label = { Text("Eliminar") })
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPosts() { PostsCrudApp() }