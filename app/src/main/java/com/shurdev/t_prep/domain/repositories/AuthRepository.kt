package com.shurdev.t_prep.domain.repositories

import androidx.credentials.GetCredentialResponse
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val isAuthenticated: StateFlow<Boolean?>

    suspend fun login(credential: GetCredentialResponse)

    suspend fun setup()
}