package com.shurdev.t_prep.domain.repositories

import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val isAuthenticated: StateFlow<Boolean?>

    suspend fun login()

    suspend fun setup()
}