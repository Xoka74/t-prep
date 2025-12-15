package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.MeUserDto
import retrofit2.http.GET

interface MeApi {
    @GET("users/me")
    suspend fun getMeUser() : MeUserDto
}