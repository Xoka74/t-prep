package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.data.models.ListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CardsApi {
    @GET("modules/{moduleId}/cards/{cardId}")
    suspend fun getCardById(
        @Path("moduleId") moduleId: Int,
        @Path("cardId") cardId: Int,
    ): CardDto?

    @GET("modules/{moduleId}/cards")
    suspend fun getCardsByModuleId(@Path("moduleId") moduleId: Int): ListResponse<CardDto>

    @POST("modules/{moduleId}/cards")
    suspend fun createCard(
        @Path("moduleId") moduleId: Int,
        @Body data: CardData,
    ): CardDto
}