package com.shurdev.t_prep.presentation.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    val colors = MaterialTheme.colorScheme
    BaseButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        enabled = enabled,
        isLoading = isLoading,
        containerColor = colors.secondaryContainer,
        contentColor = colors.onSecondaryContainer,
    )
}


@Preview
@Composable
fun PreviewSecondaryButton() {
    Column {
        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button"
        )

        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button",
            isLoading = true,
        )

        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Some button",
            enabled = false,
        )
    }
}