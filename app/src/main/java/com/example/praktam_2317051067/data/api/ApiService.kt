package com.example.praktam_2317051067.data.api

import com.example.praktam_2317051067.data.model.Buku
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getBuku(@Url url: String): List<Buku>
}

object RetrofitClient {
    val apiService: ApiService = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}