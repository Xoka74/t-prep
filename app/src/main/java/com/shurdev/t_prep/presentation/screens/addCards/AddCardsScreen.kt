package com.shurdev.t_prep.presentation.screens.addCards

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.forms.EditableState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.forms.FormSubmittingState
import com.shurdev.t_prep.domain.forms.FormValidationErrorState
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.components.layout.StickyBottomColumn
import com.shurdev.t_prep.presentation.screens.addCards.viewModel.AddCardsViewModel
import com.shurdev.t_prep.presentation.screens.addCards.viewModel.form.CardsFormValidationError
import com.shurdev.t_prep.presentation.screens.createModule.components.CardsCreationList
import com.shurdev.t_prep.presentation.screens.createModule.components.CreateModuleButtons

@Composable
fun AddCardsScreen(
    onBack: () -> Unit,
) {
    val viewModel = hiltViewModel<AddCardsViewModel>()
    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? CardsFormValidationError

    LaunchedEffect(formState) {
        if (formState is FormSubmittedState<*>) {
            onBack()
        }
    }

    var showFilePicker by remember { mutableStateOf(false) }

    DefaultScreenLayout(
        title = stringResource(R.string.add_cards),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        onBackInvoked = onBack
    ) {
        StickyBottomColumn(
            stickyBottom = {
                CreateModuleButtons(
                    onAddCardClick = viewModel::onAddCard,
                    onImportClick = { showFilePicker = true },
                    onSubmitClick = viewModel::submitForm,
                    isLoading = formState is FormSubmittingState,
                    submitEnabled = formState is EditableState
                )
            }
        ) {
            FilePicker(show = showFilePicker, fileExtensions = listOf("csv")) { platformFile ->
                platformFile?.let(viewModel::importCards)

                showFilePicker = false
            }

            if (form.cards.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.cards),
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(Modifier.height(8.dp))

                CardsCreationList(
                    cards = form.cards,
                    onCardChange = viewModel::onCardChange,
                    onCardRemove = viewModel::onCardRemove,
                )
            } else {
                Text("Добавьте или импортируйте карточки")
            }
        }
    }
}