package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.MeApi
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.domain.models.MeUser
import javax.inject.Inject

class MeRepositoryImpl @Inject constructor(
    private val meApi: MeApi,
) : MeRepository {
    override suspend fun getUser(): MeUser {
        return meApi.getMeUser().toDomainModel()
    }
}