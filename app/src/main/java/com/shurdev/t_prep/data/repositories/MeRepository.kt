package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.domain.models.MeUser

interface MeRepository {
    suspend fun getUser(): MeUser
}