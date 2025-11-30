package com.shurdev.t_prep.presentation.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import com.shurdev.t_prep.presentation.components.dialogs.SingleChoiceDialog

@Composable
fun <T> SingleChoiceDialogButton(
    modifier: Modifier = Modifier,
    title: String,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    itemToString: @Composable (T) -> String,
    placeholderText: String? = null,
    selectedItem: T? = null,
) {
    val typography = MaterialTheme.typography

    var expanded by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    Column {
        Box(
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                expanded = true
            },
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = typography.titleMedium
                )

                Spacer(Modifier.weight(1f))

                if (selectedItem != null) {
                    Text(
                        text = itemToString(selectedItem),
                        style = typography.titleSmall,
                    )
                } else if (placeholderText != null) {
                    Text(placeholderText)
                }

                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
            }
        }

        if (expanded) {
            SingleChoiceDialog(
                items = items,
                initialSelectedItem = selectedItem,
                itemToString = itemToString,
                onSelect = { selected ->
                    selected?.let(onItemSelected)
                    expanded = false
                },
                onDismiss = {
                    expanded = false
                },
            )
        }
    }
}

