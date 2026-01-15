package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleCreatedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleDeletedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEditedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.models.Module
import com.shurdev.t_prep.domain.repositories.ModuleRepository

class ModuleRepositoryImpl(
    private val moduleDao: ModuleDao,
    private val modulesApi: ModulesApi,
    private val moduleEventPublisher: ModuleEventPublisher,
) : ModuleRepository {
    override suspend fun getUserModules(search: String): List<Module> {
        return modulesApi.getModules("only_me", search).items.map { it.toDomainModel() }
    }

    override suspend fun getAllModules(search: String): List<Module> {
        return modulesApi.getModules("all_users", search).items.map { it.toDomainModel() }
    }

    override suspend fun getModuleById(id: String): Module? {
        return modulesApi.getModule(id.toInt())?.toDomainModel()
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

    override suspend fun editModule(moduleId: Int, data: ModuleData) {
        modulesApi.editModule(moduleId, data).toDomainModel()

        moduleEventPublisher.push(ModuleEditedEvent(moduleId.toString()))
    }

    override suspend fun deleteModule(id: Int) {
        modulesApi.deleteModule(id)

        moduleEventPublisher.push(ModuleDeletedEvent(id.toString()))
    }
}