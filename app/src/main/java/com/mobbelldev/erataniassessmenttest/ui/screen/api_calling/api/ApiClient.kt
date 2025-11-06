package com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://gorest.co.in/public/v2/"
    private const val TOKEN = "Bearer 50ae48fc50aa2c8c038f080cf7cd6696d29b432589e3f2ef3fa7e62942423517"

    private val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", TOKEN)
            .addHeader("Accept", "application/json")
            .build()
        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val api: GoRestAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoRestAPI::class.java)
    }
}