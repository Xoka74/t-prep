package com.shurdev.t_prep.presentation.screens.createModule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        Row {
            SecondaryButton(
                "Добавить карточку",
                onClick = onAddCardClick,
            )

            Spacer(Modifier.weight(1f))

            SecondaryButton(
                "Импортировать",
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