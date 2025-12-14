package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleCreatedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.models.Module
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.utils.runSuspendCatching

class ModuleRepositoryImpl(
    private val moduleDao: ModuleDao,
    private val modulesApi: ModulesApi,
    private val moduleEventPublisher: ModuleEventPublisher,
) : ModuleRepository {
    override suspend fun getModules(): List<Module> {
        return modulesApi.getUserModules().map { it.toDomainModel() }
    }

    override suspend fun getModuleById(id: String): Module? {
        return modulesApi.getUserModule(id.toInt())?.toDomainModel()
    }

    override suspend fun updateModuleProgress(moduleId: String, completed: Int) {
        val module = moduleDao.getModuleById(moduleId)
        module?.let {
            moduleDao.updateModule(it.copy(completedCards = completed))
        }
    }

    override suspend fun createModule(data: ModuleData): Module {
        val module = modulesApi.createModule(data).toDomainModel()

        moduleEventPublisher.push(ModuleCreatedEvent())

        return module
    }
}