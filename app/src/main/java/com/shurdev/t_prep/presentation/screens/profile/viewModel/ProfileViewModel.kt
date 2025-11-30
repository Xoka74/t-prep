package com.shurdev.t_prep.presentation.screens.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.data.repositories.MeRepository
import com.shurdev.t_prep.domain.repositories.LogoutRepository
import com.shurdev.t_prep.presentation.components.viewModel.BaseViewModel
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val meRepository: MeRepository,
    private val logoutRepository: LogoutRepository,
) : BaseViewModel<ProfileUiState>(ProfileLoadingState) {
    init {
        loadProfile()
    }

    fun loadProfile() {
        updateUiState { ProfileLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                val user = meRepository.getUser()
                updateUiState { ProfileLoadedState(user) }
            }.onFailure {
                updateUiState { ProfileErrorState }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutRepository.logout()
        }
    }
}