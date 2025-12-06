package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.domain.repositories.LogoutRepository
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : LogoutRepository {
    override suspend fun logout() {
        authDataSource.clear()
    }
}