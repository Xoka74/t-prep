package com.shurdev.t_prep.presentation.screens.moduleSettings.form

import com.shurdev.t_prep.domain.forms.Form
import com.shurdev.domain.forms.ValidationError
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.models.AccessLevel
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleFormValidationError

data class EditModuleForm(
    val initialData: ModuleData = ModuleData(
        name = "",
        description = "",
        viewAccess = AccessLevel.AllUsers,
        editAccess = AccessLevel.AllUsers,
        isIntervalRepetitionsEnabled = false
    ),
    val module: ModuleData = ModuleData(
        name = "",
        description = "",
        viewAccess = AccessLevel.AllUsers,
        editAccess = AccessLevel.AllUsers,
        isIntervalRepetitionsEnabled = false
    ),
    val totalCards: Int? = null,
) : Form<ModuleFormValidationError>() {
    override fun validate(): ModuleFormValidationError? {
        if (module.name.isEmpty()) {
            return ModuleFormValidationError(nameError = ValidationError.Required)
        }

        return null
    }
}