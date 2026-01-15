package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.models.Module
import retrofit2.http.Path

interface ModuleRepository {
    suspend fun getUserModules(search: String): List<Module>
    suspend fun getAllModules(search: String): List<Module>
    suspend fun getModuleById(id: String): Module?
    suspend fun updateModuleProgress(moduleId: String, completed: Int)
    suspend fun createModule(data: ModuleData) : Module
    suspend fun editModule(moduleId: Int, data: ModuleData)
    suspend fun deleteModule(id: Int)
}