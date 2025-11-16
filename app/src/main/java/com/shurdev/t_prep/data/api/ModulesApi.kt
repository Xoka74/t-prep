package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.ModuleDto
import retrofit2.http.DELETE
import retrofit2.http.GET

interface ModulesApi {
    @GET("modules")
    suspend fun getUserModules(): List<ModuleDto>

    @GET("modules/{id}")
    suspend fun getUserModule(id: Int): ModuleDto

    @DELETE("modules/{id}")
    suspend fun deleteUserModule(id: Int)
}