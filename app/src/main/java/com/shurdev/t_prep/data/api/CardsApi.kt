package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.CardDto
import retrofit2.http.GET

interface CardsApi {
    @GET("cards/{id}")
    suspend fun getCardById(id: Int): CardDto?

    @GET("modules/module{id}")
    suspend fun getCardsByModuleId(id: Int): List<CardDto>
}