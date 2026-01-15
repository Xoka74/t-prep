package com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form

import com.shurdev.domain.forms.FormValidationError
import com.shurdev.domain.forms.ValidationError

data class EditCardValidationError(
    val questionError: ValidationError? = null,
    val answerError: ValidationError? = null,
) : FormValidationError()