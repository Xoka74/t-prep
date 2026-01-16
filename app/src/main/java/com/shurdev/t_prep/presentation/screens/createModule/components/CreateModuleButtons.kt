package com.shurdev.t_prep.presentation.screens.createModule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.buttons.PrimaryButton
import com.shurdev.t_prep.presentation.components.buttons.SecondaryButton

@Composable
fun CreateModuleButtons(
    onAddCardClick: () -> Unit,
    onImportClick: () -> Unit,
    onSubmitClick: () -> Unit,
    isLoading: Boolean,
    submitEnabled: Boolean,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SecondaryButton(
                modifier = Modifier.weight(1f),
                text = "Добавить",
                onClick = onAddCardClick,
            )

            Spacer(Modifier.width(8.dp))

            SecondaryButton(
                modifier = Modifier.weight(1f),
                text = "Импорт",
                onClick = onImportClick,
            )
        }

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.save),
            onClick = onSubmitClick,
            isLoading = isLoading,
            enabled = submitEnabled
        )
    }
}