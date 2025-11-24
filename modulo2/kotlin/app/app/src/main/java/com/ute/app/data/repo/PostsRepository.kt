package com.ute.app.data.repo

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ute.app.data.api.PostsApi
import com.ute.app.data.model.PostDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class PostsRepository(
    baseUrl: String = "https://jsonplaceholder.typicode.com/"
) {
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }
    private val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    private val contentType = "application/json".toMediaType()

    private val api: PostsApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(client)
        .build()
        .create(PostsApi::class.java)

    suspend fun fetchPosts(): List = api.getPosts().take(20)
    suspend fun fetchPost(id: Int): PostDTO = api.getPost(id)
    suspend fun create(title: String, body: String): PostDTO = api.createPost(PostDTO(title = title, body = body))
}