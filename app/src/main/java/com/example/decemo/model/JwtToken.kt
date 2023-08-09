package com.example.decemo.model

import java.util.Date

data class JwtToken(
    var token: String,
    var refreshToken: String,
    var expiredAt: Date
)
