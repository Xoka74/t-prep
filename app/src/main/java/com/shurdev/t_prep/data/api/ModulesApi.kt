package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.ListResponse
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.data.models.ModuleDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ModulesApi {
    @GET("modules")
    suspend fun getUserModules(): ListResponse<ModuleDto>

    @GET("modules/{id}")
    suspend fun getUserModule(id: Int): ModuleDto?

    @DELETE("modules/{id}")
    suspend fun deleteUserModule(id: Int)

    @POST("modules/")
    suspend fun createModule(@Body data: ModuleData): ModuleDto
}