package com.shurdev.t_prep.presentation.screens.profile.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.t_prep.data.models.MeUserDto
import com.shurdev.t_prep.domain.models.MeUser

sealed interface ProfileUiState

data object ProfileLoadingState : ProfileUiState

@Stable
data class ProfileLoadedState(val user: MeUser) : ProfileUiState

@Stable
data object ProfileErrorState : ProfileUiState