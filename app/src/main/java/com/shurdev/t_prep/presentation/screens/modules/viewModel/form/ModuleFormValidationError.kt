package com.shurdev.t_prep.presentation.screens.modules.viewModel.form

import com.shurdev.domain.forms.FormValidationError
import com.shurdev.domain.forms.ValidationError

data class ModuleFormValidationError(
    val nameError: ValidationError? = null,
    val descriptionError: ValidationError? = null,
) : FormValidationError()