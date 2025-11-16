package com.shurdev.t_prep.presentation.screens.modules

import com.shurdev.t_prep.domain.models.Module

data class ModulesState(
    val modules: List<Module> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
