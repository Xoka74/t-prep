package com.shurdev.t_prep.presentation.screens.dialogs.editCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.forms.FormSubmittingState
import com.shurdev.t_prep.domain.forms.FormValidationErrorState
import com.shurdev.t_prep.presentation.components.buttons.PrimaryButton
import com.shurdev.t_prep.presentation.components.buttons.SecondaryButton
import com.shurdev.t_prep.presentation.components.loaders.Loader
import com.shurdev.t_prep.presentation.components.textFields.AppTextField
import com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.EditCardViewModel
import com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form.EditCardValidationError

@Composable
fun EditCardDialog(
    onBack: () -> Unit,
) {
    val viewModel = hiltViewModel<EditCardViewModel>()

    val form by viewModel.formDataState.collectAsState()

    val formState by viewModel.uiState.collectAsState()

    val cardData = form.cardData

    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? EditCardValidationError

    val isSubmitting = formState is FormSubmittingState

    LaunchedEffect(formState) {
        if (formState is FormSubmittedState) {
            onBack()
        }
    }

    Dialog(
        onDismissRequest = onBack,
    ) {
        when {
            isSubmitting -> Loader()
            else -> {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                    ) {
                        AppTextField(
                            modifier = Modifier.fillMaxWidth(),
                            text = cardData.question,
                            hint = stringResource(R.string.question),
                            error = validationError?.questionError?.toErrorString(
                                required = stringResource(R.string.required_field),
                            ),
                            onTextChange = viewModel::onQuestionChange,
                        )

                        Spacer(Modifier.height(8.dp))

                        AppTextField(
                            modifier = Modifier.fillMaxWidth(),
                            text = cardData.answer,
                            hint = stringResource(R.string.answer),
                            error = validationError?.answerError?.toErrorString(
                                required = stringResource(R.string.required_field),
                            ),
                            onTextChange = viewModel::onAnswerChange,
                        )

                        Spacer(Modifier.height(8.dp))

                        Row {
                            SecondaryButton(
                                text = stringResource(R.string.cancel),
                                onClick = onBack
                            )

                            Spacer(Modifier.weight(1f))
                            PrimaryButton(
                                text = stringResource(R.string.save),
                                onClick = viewModel::submitForm
                            )
                        }
                    }
                }
            }
        }
    }
}