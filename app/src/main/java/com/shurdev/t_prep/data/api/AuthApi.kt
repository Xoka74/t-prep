package com.shurdev.t_prep.data.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auth/google/android")
    suspend fun postLoginIdToken(@Query("id_token") idToken: String) : Response<Any>
}