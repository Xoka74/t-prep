package com.shurdev.t_prep.presentation.screens.addCards.viewModel.form

import com.shurdev.domain.forms.FormValidationError
import com.shurdev.domain.forms.ValidationError

data class CardsFormValidationError(
    val nameError: ValidationError? = null,
    val descriptionError: ValidationError? = null,
) : FormValidationError()