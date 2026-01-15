package com.shurdev.t_prep.presentation.screens.modules.viewModel.form

import com.shurdev.t_prep.domain.forms.Form
import com.shurdev.domain.forms.ValidationError
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.models.AccessLevel

data class ModuleForm(
    val module: ModuleData = ModuleData(
        name = "",
        description = "",
        viewAccess = AccessLevel.AllUsers,
        editAccess = AccessLevel.AllUsers,
        passwordHash = "string",
        isIntervalRepetitionsEnabled = false
    ),
    val cards: List<CardData> = listOf()
) : Form<ModuleFormValidationError>() {
    override fun validate(): ModuleFormValidationError? {
        if (module.name.isEmpty()) {
            return ModuleFormValidationError(nameError = ValidationError.Required)
        }

        return null
    }
}