package com.example.decemo.model

data class User(
    val id: Long,
    val email: String,
    val fullName: String,
    val role: String?
)