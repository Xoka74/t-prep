package com.shurdev.t_prep.presentation.screens.createModule

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.forms.EditableState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.domain.forms.FormSubmittingState
import com.shurdev.domain.forms.FormValidationErrorState
import com.shurdev.t_prep.R
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.presentation.components.buttons.PrimaryButton
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.components.layout.StickyBottomColumn
import com.shurdev.t_prep.presentation.components.textFields.AppTextField
import com.shurdev.t_prep.presentation.screens.createModule.components.CardsCreationView
import com.shurdev.t_prep.presentation.screens.createModule.viewModel.CreateModuleViewModel
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleFormValidationError

@Composable
fun CreateModuleScreen(
    onBackInvoked: () -> Unit,
) {
    val viewModel = hiltViewModel<CreateModuleViewModel>()
    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? ModuleFormValidationError

    LaunchedEffect(formState) {
        if (formState is FormSubmittedState<*>) {
            onBackInvoked()
        }
    }

    DefaultScreenLayout(
        title = stringResource(R.string.create_module),
        modifier = Modifier.padding(horizontal = 16.dp),
        onBackInvoked = onBackInvoked
    ) {
        StickyBottomColumn(
            stickyBottom = {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.create),
                    onClick = viewModel::submitForm,
                    isLoading = formState is FormSubmittingState,
                    enabled = formState is EditableState
                )
            }
        ) {
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = form.module.name,
                hint = stringResource(R.string.title),
                onTextChange = {
                    viewModel.updateFormData { form -> form.copy(module = form.module.copy(name = it)) }
                },
                error = validationError?.nameError?.toErrorString(
                    required = stringResource(R.string.required_field),
                ),
            )

            Spacer(Modifier.height(12.dp))

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = form.module.description,
                hint = stringResource(R.string.add_description),
                onTextChange = {
                    viewModel.updateFormData { form ->
                        form.copy(
                            module = form.module.copy(
                                description = it
                            )
                        )
                    }
                },
                error = validationError?.descriptionError?.toErrorString(
                    required = stringResource(R.string.required_field),
                ),
            )

            CardsCreationView(
                cards = form.cards,
                onCardAdd = {
                    viewModel.updateFormData { form ->
                        form.copy(cards = form.cards + CardData("", ""))
                    }
                },
                onCardChange = { index, card ->
                    viewModel.updateFormData { form ->
                        form.copy(cards = form.cards.mapIndexed { cardIndex, item ->
                            if (cardIndex == index) card else item
                        })
                    }
                }
            )
        }
    }
}