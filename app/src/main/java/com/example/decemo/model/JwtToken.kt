package com.example.decemo.retrofit.dto

import java.util.Date

data class JwtToken(
    var token: String,
    var refreshToken: String,
    var expiredAt: Date
)
