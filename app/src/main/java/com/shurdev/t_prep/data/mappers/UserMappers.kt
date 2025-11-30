package com.shurdev.t_prep.data.mappers

import com.shurdev.t_prep.data.models.MeUserDto
import com.shurdev.t_prep.domain.models.MeUser

fun MeUserDto.toDomainModel() : MeUser {
    return MeUser(
        id = id,
        name = name,
    )
}