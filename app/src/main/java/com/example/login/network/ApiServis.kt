package com.example.login.network

import com.example.login.model.LoginRequest
import com.example.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login") // Endpoint login
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
