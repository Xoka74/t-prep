package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.Module

interface ModuleRepository {
    suspend fun getModules(): List<Module>
    suspend fun getModuleById(id: String): Module?
    suspend fun updateModuleProgress(moduleId: String, completed: Int)
}