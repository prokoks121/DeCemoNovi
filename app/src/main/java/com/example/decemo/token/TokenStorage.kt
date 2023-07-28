package com.example.decemo.token

import android.app.Application

class TokenStorage(private val context: Application) {
    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }

    fun putAccessToken(value: String) {
        context.putString(ACCESS_TOKEN, value)
    }

    fun putRefreshToken(value: String) {
        context.putString(REFRESH_TOKEN, value)
    }

    fun getAccessToken(): String? = context.getString(ACCESS_TOKEN)

    fun getRefreshToken(): String? = context.getString(REFRESH_TOKEN)

    fun clearTokens() {
        context.putString(ACCESS_TOKEN, "")
        context.putString(REFRESH_TOKEN, "")
    }
}