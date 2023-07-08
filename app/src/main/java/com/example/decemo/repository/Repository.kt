package com.example.decemo.repository

import com.example.decemo.retrofit.ApiConnection
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarTypeDto
import com.example.decemo.retrofit.dto.EventDto

class Repository(private val connection: ApiConnection) {

    //TODO vracati result, napraviti ekstenziju koja ce da proverava da li je 2xx
    suspend fun getAllBars(): Result<MutableList<BarDto>> {
        val response = connection.getAllBars()
        return Result.success(response.body() ?: mutableListOf())
    }

    suspend fun getFilteredBarsByType(filterTypes: List<Long>): Result<MutableList<BarDto>> {
        val response = connection.getFilteredBarsByType(filterTypes)
        return Result.success(response.body() ?: mutableListOf())
    }

    suspend fun getAllBarTypes(): Result<MutableList<BarTypeDto>> {
        val response = connection.getAllBarTypes()
        return Result.success(response.body() ?: mutableListOf())
    }

    suspend fun getBar(barId: Long): Result<BarDto> {
        val response = connection.getBar(barId)
        //TODO Hendlati ako nema
        return Result.success(response.body()!!)
    }

    suspend fun getAllEvents(): Result<MutableList<EventDto>> {
        val call = connection.getListOfEvents()
        return runCatching {
            call.execute().body()!!
        }
    }
}