package com.shurdev.t_prep.data.models

import com.shurdev.t_prep.domain.models.Card

data class ModuleDto(
    val id: Int,
    val ownerId: Int,
    val name: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val cards: List<Card>? = null,
)