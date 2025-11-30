package com.shurdev.t_prep.data.api

import com.shurdev.t_prep.data.models.MeUserDto

interface MeApi {
    suspend fun getMeUser() : MeUserDto
}