package com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.api

import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.model.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GoRestAPI {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @POST("users")
    suspend fun registerUser(@Body user: UserResponse): UserResponse
}