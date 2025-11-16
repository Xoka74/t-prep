package com.shurdev.t_prep.presentation.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: Painter? = null,
) {
    val colors = MaterialTheme.colorScheme

    BaseButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        enabled = enabled,
        isLoading = isLoading,
        icon = icon,
        containerColor = colors.primaryContainer,
        contentColor = colors.onPrimaryContainer,
    )
}


@Preview
@Composable
fun PreviewButton() {
    Column {
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button"
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button",
            isLoading = true,
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button",
            enabled = false,
        )
    }
}