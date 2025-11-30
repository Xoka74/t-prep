package com.shurdev.t_prep.data.repositories

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.shurdev.t_prep.domain.repositories.LogoutRepository
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val credentialManager: CredentialManager,
) : LogoutRepository {
    override suspend fun logout() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        authDataSource.clear()
    }
}