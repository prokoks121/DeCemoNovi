package com.example.decemo.retrofit.dto

data class UserDto(
    val id: Long,
    val email: String,
    val fullName: String,
    val role: String?
)