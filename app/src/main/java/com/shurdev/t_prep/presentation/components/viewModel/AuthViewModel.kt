package com.shurdev.t_prep.presentation.components.viewModel

import com.shurdev.t_prep.domain.repositories.AuthRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val isAuthenticated = authRepository.isAuthenticated

    init {
        viewModelScope.launch {
            authRepository.setup()
        }
    }
}