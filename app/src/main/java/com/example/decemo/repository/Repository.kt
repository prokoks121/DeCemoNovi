package com.example.decemo.repository

import com.example.decemo.retrofit.ApiConnection
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.EventDto

class Repository(private val connection: ApiConnection) {

    suspend fun getAllBars(): Result<MutableList<BarDto>> {
//        return runCatching {
            val response = connection.getListOfLocals()
            return Result.success(response.body()!!)
//        }
    }

    suspend fun getAllEvents(): Result<MutableList<EventDto>> {
        val call = connection.getListOfEvents()
        return runCatching {
            call.execute().body()!!
        }
    }
}