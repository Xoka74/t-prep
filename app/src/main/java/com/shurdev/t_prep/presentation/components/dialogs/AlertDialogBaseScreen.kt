package com.shurdev.t_prep.presentation.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.viewModel.alertDialog.AlertDialogDoneState
import com.shurdev.t_prep.presentation.components.viewModel.alertDialog.AlertDialogErrorState
import com.shurdev.t_prep.presentation.components.viewModel.alertDialog.AlertDialogLoadingState
import com.shurdev.t_prep.presentation.components.viewModel.alertDialog.AlertDialogViewModel

@Composable
fun AlertDialogBaseScreen(
    viewModel: AlertDialogViewModel,
    title: String,
    text: String,
    onDismissRequest: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val inProgress = state is AlertDialogLoadingState
    val error = if (state is AlertDialogErrorState) "Неизвестная ошибка" else null

    LaunchedEffect(state) {
        if (state is AlertDialogDoneState) {
            onDismissRequest()
        }
    }

    AlertDialog(
        icon = { Icon(Icons.Default.Info, contentDescription = null) },
        title = { Text(title) },
        text = {
            if (inProgress) {
                Row {
                    Spacer(Modifier.weight(1f))
                    CircularProgressIndicator()
                    Spacer(Modifier.weight(1f))
                }
            } else {
                Column {
                    if (error != null) {
                        Text(
                            text = error,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.error,
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            if (!inProgress) {
                TextButton(onClick = {
                    viewModel.onConfirm()
                }) {
                    Text(
                        text = stringResource(R.string.confirm),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        },
        dismissButton = {
            if (!inProgress) {
                TextButton(onClick = onDismissRequest) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    )
}
