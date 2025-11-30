package com.shurdev.t_prep.presentation.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable

@Composable
fun ProfileMenu(
    onLogoutClick: () -> Unit = {},
) {
    Column {
        HorizontalDivider()

        LogoutButton(
            onClick = onLogoutClick,
        )
    }
}