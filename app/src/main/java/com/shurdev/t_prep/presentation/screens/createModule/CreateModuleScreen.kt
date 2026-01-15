package com.shurdev.t_prep.presentation.screens.createModule

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.forms.EditableState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.forms.FormSubmittingState
import com.shurdev.t_prep.domain.forms.FormValidationErrorState
import com.shurdev.t_prep.domain.models.AccessLevel
import com.shurdev.t_prep.presentation.components.buttons.PrimaryButton
import com.shurdev.t_prep.presentation.components.buttons.SingleChoiceDialogButton
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.components.layout.StickyBottomColumn
import com.shurdev.t_prep.presentation.components.textFields.AppTextField
import com.shurdev.t_prep.presentation.screens.createModule.viewModel.CreateModuleViewModel
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleFormValidationError
import com.shurdev.t_prep.presentation.utils.toResString

@Composable
fun CreateModuleScreen(
    onBackInvoked: () -> Unit,
    onModuleCreated: (moduleId: String) -> Unit,
) {
    val viewModel = hiltViewModel<CreateModuleViewModel>()
    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? ModuleFormValidationError

    LaunchedEffect(formState) {
        val state = formState
        if (state is FormSubmittedState<*>) {
            val moduleId = (state.data as? String) ?: return@LaunchedEffect
            onModuleCreated(moduleId)
        }
    }

    DefaultScreenLayout(
        title = stringResource(R.string.create_module),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        onBackInvoked = onBackInvoked
    ) {
        StickyBottomColumn(
            stickyBottom = {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = viewModel::submitForm,
                    isLoading = formState is FormSubmittingState,
                    enabled = formState is EditableState,
                    text = stringResource(R.string.next)
                )
            }
        ) {
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = form.module.name,
                hint = stringResource(R.string.title),
                onTextChange = viewModel::onModuleNameChanged,
                error = validationError?.nameError?.toErrorString(
                    required = stringResource(R.string.required_field),
                ),
            )

            Spacer(Modifier.height(12.dp))

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = form.module.description,
                hint = stringResource(R.string.add_description),
                onTextChange = viewModel::onModuleDescriptionChanged,
                error = validationError?.descriptionError?.toErrorString(
                    required = stringResource(R.string.required_field),
                ),
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.access_settings),
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                SingleChoiceDialogButton(
                    modifier = Modifier.padding(16.dp),
                    title = stringResource(R.string.viewing),
                    items = AccessLevel.entries,
                    onItemSelected = viewModel::onViewAccessChange,
                    itemToString = { it.toResString() },
                    selectedItem = form.module.viewAccess,
                )
            }

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                SingleChoiceDialogButton(
                    modifier = Modifier.padding(16.dp),
                    title = stringResource(R.string.editing),
                    items = AccessLevel.entries,
                    onItemSelected = viewModel::onEditAccessChange,
                    itemToString = { it.toResString() },
                    selectedItem = form.module.editAccess,
                )
            }
        }
    }
}