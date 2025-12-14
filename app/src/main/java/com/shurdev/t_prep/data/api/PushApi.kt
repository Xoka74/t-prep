package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.AuthData
import com.shurdev.t_prep.data.models.PushTokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PushApi {
    @POST("users/push-token")
    suspend fun pushDeviceToken(@Body data: PushTokenDto) : AuthData
}