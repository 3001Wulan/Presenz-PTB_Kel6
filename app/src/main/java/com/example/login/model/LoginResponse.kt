package com.example.login.model

data class LoginResponse(
    val message: String,
    val token: String,
    val refreshToken: String,
    val user: User,
    val status: String
)

data class User(
    val id_user: Int,
    val nama: String,
    val email: String,
    val role: String
)

