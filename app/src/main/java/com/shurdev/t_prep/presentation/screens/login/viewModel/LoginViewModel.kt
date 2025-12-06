package com.shurdev.t_prep.presentation.screens.login.viewModel

import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.repositories.AuthRepository
import com.shurdev.t_prep.presentation.components.viewModel.BaseViewModel
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<LoginUiState>(LoginIdleState()) {
    fun login(credential: GetCredentialResponse) {
        updateUiState { LoginLoadingState }

        viewModelScope.launch {
            runSuspendCatching {
                authRepository.login(credential)
            }.onSuccess {
                updateUiState { LoginSuccessState }
            }.onFailure { it ->
                print(it)
                updateUiState { LoginIdleState(error = "Неизвестная ошибка") }
            }
        }
    }
}