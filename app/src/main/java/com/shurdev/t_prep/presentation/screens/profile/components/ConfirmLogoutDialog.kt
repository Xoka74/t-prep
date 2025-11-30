package com.shurdev.t_prep.presentation.screens.profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.dialogs.BaseConfirmDialog

@Composable
fun ConfirmLogoutDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    val confirmActionTitle = stringResource(R.string.are_you_sure_you_want_to_logout)
    val continueText = stringResource(R.string.continue_text)
    val cancelText = stringResource(R.string.cancel)

    BaseConfirmDialog(
        icon = Icons.AutoMirrored.Filled.ExitToApp,
        title = confirmActionTitle,
        confirmText = continueText,
        dismissText = cancelText,
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    )
}