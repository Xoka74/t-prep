package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.domain.models.Module
import com.shurdev.t_prep.domain.repositories.ModuleRepository

class ModuleRepositoryImpl(
    private val moduleDao: ModuleDao,
    private val modulesApi: ModulesApi,
) : ModuleRepository {

    val modules = (0..10).map {
        Module(
            it.toString(),
            "Новый модуль",
            "Описание",
            10,
            10
        )
    }

    override suspend fun getModules(): List<Module> {
        return modules

//        return modulesApi.getUserModules().map { it.toDomainModel() }
    }

    override suspend fun getModuleById(id: String): Module? {
        return modules.firstOrNull { it.id == id }
//        return modulesApi.getUserModule(id.toInt()).toDomainModel()
    }

    override suspend fun updateModuleProgress(moduleId: String, completed: Int) {
        val module = moduleDao.getModuleById(moduleId)
        module?.let {
            moduleDao.updateModule(it.copy(completedCards = completed))
        }
    }
}