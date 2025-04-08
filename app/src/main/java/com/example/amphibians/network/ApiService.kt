package com.example.amphibians.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



object AmphibianApi{
    private val json = Json {
        ignoreUnknownKeys = true // ✅ This is the key!
    }
    private const val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
interface ApiService{
    @GET("amphibians")
    suspend fun getData(): List<AmphibianData>
}