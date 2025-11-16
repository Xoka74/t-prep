package com.shurdev.t_prep.domain.usecases


import com.shurdev.t_prep.domain.models.Module
import com.shurdev.t_prep.domain.repositories.ModuleRepository

class GetModulesUseCase(
    private val repository: ModuleRepository
) {
    suspend operator fun invoke(): List<Module> = repository.getModules()
}
