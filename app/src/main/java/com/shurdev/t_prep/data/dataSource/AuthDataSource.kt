package com.shurdev.t_prep.data.dataSource

import android.content.SharedPreferences
import com.shurdev.t_prep.data.constants.LocalKeys
import com.shurdev.t_prep.data.models.AuthData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthDataSource(
    private val prefs: SharedPreferences,
) {
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    fun setup() {
        val authenticated = accessToken() != null

        return _isAuthenticated.update { authenticated }
    }

    fun accessToken(): String? = prefs.getString(LocalKeys.ACCESS_TOKEN, null)

    fun save(authData: AuthData) {
        with(prefs.edit()) {
            putString(LocalKeys.ACCESS_TOKEN, authData.accessToken)
            apply()
        }

        return _isAuthenticated.update { true }
    }

    fun clear() {
        with(prefs.edit()) {
            remove(LocalKeys.ACCESS_TOKEN)
            apply()
        }

        return _isAuthenticated.update { false }
    }
}