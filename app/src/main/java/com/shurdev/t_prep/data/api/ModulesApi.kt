package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.ListResponse
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.data.models.ModuleDto
import com.shurdev.t_prep.domain.models.AccessLevel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ModulesApi {
    @GET("modules")
    suspend fun getModules(
        @Query("filter") filter: String,
        @Query("search_string") search: String
    ): ListResponse<ModuleDto>

    @GET("modules/{id}")
    suspend fun getModule(@Path("id") id: Int): ModuleDto?

    @DELETE("modules/{id}")
    suspend fun deleteUserModule(@Path("id") id: Int)

    @POST("modules/")
    suspend fun createModule(@Body data: ModuleData): ModuleDto

    @PATCH("modules/{id}")
    suspend fun editModule(
        @Path("id") id: Int,
        @Body data: ModuleData
    ): ModuleDto

    @DELETE("modules/{id}")
    suspend fun deleteModule(@Path("id") id: Int)
}