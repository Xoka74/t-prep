package com.shurdev.t_prep.presentation.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AlertStateDialog(
    title: String,
    text: String,
    icon: ImageVector,
    inProgress: Boolean,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    error: String?,
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = null) },
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
                    if (error != null){
                        Text(
                            text = error,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.error,
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    Text(
                        text = text,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            if (!inProgress) {
                TextButton(onClick = onConfirm) {
                    Text("Подтвердить")
                }
            }
        },
        dismissButton = {
            if (!inProgress) {
                TextButton(onClick = onDismissRequest) {
                    Text("Отменить")
                }
            }
        }
    )
}