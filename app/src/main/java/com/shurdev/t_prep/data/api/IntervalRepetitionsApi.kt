package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.data.models.ListResponse
import com.shurdev.t_prep.data.models.UpdateCardData
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IntervalRepetitionsApi {

    @GET("modules/{moduleId}/interval-repetitions")
    suspend fun getCardsForRepetition(
        @Path("moduleId") moduleId: Int,
    ): ListResponse<CardDto>?

    /*@GET("modules/{moduleId}/interval-repetitions") // TODO поменять когда добавят ручку
    suspend fun getIsIntervalRepetitionsEnabled(
        @Path("moduleId") moduleId: Int,
    ): Boolean?*/

    @POST("modules/{moduleId}/interval-repetitions")
    suspend fun enableIntervalRepetitions(
        @Path("moduleId") moduleId: Int,
    )

    @DELETE("modules/{moduleId}/interval-repetitions")
    suspend fun disableIntervalRepetitions(
        @Path("moduleId") moduleId: Int,
    )

    @POST("modules/{moduleId}/interval-repetitions/{cardId}")
    suspend fun updateCardStatus(
        @Path("moduleId") moduleId: Int,
        @Path("cardId") cardId: Int,
        @Body updateCardData: UpdateCardData
    )
}