package com.shurdev.t_prep.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {
    @GET("auth/google/callback")
    suspend fun postLoginCode(@Query("code") authCode: String) : Response<Any>

    @GET("auth/google/android")
    suspend fun postLoginIdToken(@Query("id_token") idToken: String) : Response<Any>
}