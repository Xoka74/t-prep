package com.shurdev.t_prep.presentation.screens.subjects

import com.shurdev.t_prep.domain.models.Subject

data class SubjectsState(
    val subjects: List<Subject> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
