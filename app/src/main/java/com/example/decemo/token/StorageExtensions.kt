package com.example.decemo.token

import android.app.Application
import android.content.SharedPreferences

const val SP_NAME = "decemo_token_storage"

fun Application.putString(key: String, value: String) {
    getSpEditor().putString(key, value).apply()
}

fun Application.getString(key: String): String? {
    return getSp().getString(key, null)
}

fun Application.remove(key: String) {
    getSpEditor().remove(key).apply()
}

fun Application.getSp(): SharedPreferences = getSharedPreferences(SP_NAME, 0)

fun Application.getSpEditor(): SharedPreferences.Editor = getSp().edit()