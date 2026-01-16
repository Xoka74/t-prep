package com.shurdev.t_prep.data.mappers

import com.shurdev.t_prep.data.models.ModuleDto
import com.shurdev.t_prep.domain.models.Module

fun ModuleDto.toDomainModel() : Module {
    return Module(
        id = id.toString(),
        name = name,
        description = description,
        totalCards = totalCards,
        viewAccess = viewAccess,
        editAccess = editAccess,
        cardsToRepeatCount = cardsToRepeatCount,
        isIntervalRepetitionsEnabled = isIntervalRepetitionsEnabled
    )
}