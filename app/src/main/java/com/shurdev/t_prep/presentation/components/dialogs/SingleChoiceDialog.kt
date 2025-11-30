package com.shurdev.t_prep.presentation.components.dialogs

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.shurdev.t_prep.domain.models.ThemeType

@Composable
fun <T> SingleChoiceDialog(
    items: List<T>,
    initialSelectedItem: T?,
    itemToString: @Composable (T) -> String,
    onSelect: (T) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            items.forEach { item ->
                val selected = item == initialSelectedItem

                val interactionSource = remember { MutableInteractionSource() }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = LocalIndication.current
                        ) {
                            onSelect(item)
                        }
                        .padding(20.dp),
                ) {
                    RadioButton(
                        selected = selected,
                        interactionSource = interactionSource,
                        onClick = null,
                    )

                    Spacer(Modifier.width(20.dp))

                    Text(
                        text = itemToString(item),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    Surface {
        SingleChoiceDialog(
            items = ThemeType.entries,
            onDismiss = {},
            onSelect = {},
            initialSelectedItem = null,
            itemToString = { it.name },
        )
    }
}