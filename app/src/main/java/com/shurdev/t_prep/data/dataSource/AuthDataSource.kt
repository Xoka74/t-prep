package com.shurdev.t_prep.data.dataSource

import android.content.SharedPreferences
import com.shurdev.t_prep.data.constants.LocalKeys
import com.shurdev.t_prep.data.models.AuthData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.core.content.edit

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
        prefs.edit {
            putString(LocalKeys.ACCESS_TOKEN, authData.accessToken)
        }

        return _isAuthenticated.update { true }
    }

    fun clear() {
        prefs.edit {
            remove(LocalKeys.ACCESS_TOKEN)
        }

        return _isAuthenticated.update { false }
    }
}