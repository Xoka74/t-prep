package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.data.models.CardDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CardsApi {
    @GET("cards/{id}")
    suspend fun getCardById(id: Int): CardDto?

    @GET("cards/module/{id}")
    suspend fun getCardsByModuleId(@Path("id") id: Int): List<CardDto>

    @POST("cards")
    suspend fun createCard(@Body data: CardDataDto): CardDto
}