package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.data.models.ListResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IntervalRepetitionsApi {

    @GET("modules/{moduleId}/interval-repetitions")
    suspend fun getCardsForRepetition(
        @Path("moduleId") moduleId: Int,
    ): ListResponse<CardDto>? // TODO не уверен что будет работать. Т.к. в json-е с бэка немного другие имена

    @GET("modules/{moduleId}/interval-repetitions") // TODO поменять когда добавят ручку
    suspend fun getIsIntervalRepetitionsEnabled(
        @Path("moduleId") moduleId: Int,
    ): Boolean?

    @POST("modules/{moduleId}/interval-repetitions")
    suspend fun enableIntervalRepetitions(
        @Path("moduleId") moduleId: Int,
    )

    @DELETE("modules/{moduleId}/interval-repetitions")
    suspend fun disableIntervalRepetitions(
        @Path("moduleId") moduleId: Int,
    )
}