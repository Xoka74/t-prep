package com.shurdev.t_prep.presentation.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R

@Composable
fun LogoutButton(
    onClick: () -> Unit,
) {
    var showAlertDialog by remember { mutableStateOf(false) }

    if (showAlertDialog) {
        ConfirmLogoutDialog(
            onDismissRequest = {
                showAlertDialog = false
            },
            onConfirmation = {
                showAlertDialog = false
                onClick()
            },
        )
    }

    Row(
        modifier = Modifier
            .clickable(onClick = {
                showAlertDialog = true
            })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.logout),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.error,
            ),
        )
        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            tint = MaterialTheme.colorScheme.error,
            contentDescription = null,
        )
    }
}