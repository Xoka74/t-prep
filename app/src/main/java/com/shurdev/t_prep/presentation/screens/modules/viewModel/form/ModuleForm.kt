package com.shurdev.t_prep.presentation.screens.modules.viewModel.form

import com.shurdev.domain.forms.Form
import com.shurdev.domain.forms.ValidationError

data class ModuleForm(
    val name: String = "",
    val description: String = "",
) : Form<ModuleFormValidationError>() {
    override fun validate(): ModuleFormValidationError? {
        if (name.isEmpty()) {
            return ModuleFormValidationError(nameError = ValidationError.Required)
        }

        return null
    }
}