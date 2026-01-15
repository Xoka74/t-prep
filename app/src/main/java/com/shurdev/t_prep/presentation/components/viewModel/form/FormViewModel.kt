package com.shurdev.t_prep.presentation.components.viewModel.form

import com.shurdev.t_prep.domain.forms.Form
import com.shurdev.t_prep.domain.forms.FormEditingState
import com.shurdev.t_prep.domain.forms.FormState
import com.shurdev.domain.forms.FormValidationError
import com.shurdev.t_prep.domain.forms.FormValidationErrorState
import com.shurdev.t_prep.presentation.components.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class FormViewModel<TError : FormValidationError, TData : Form<TError>>(
    initialData: TData,
) : BaseViewModel<FormState>(FormEditingState) {
    private val _formDataState: MutableStateFlow<TData> = MutableStateFlow(initialData)
    val formDataState = _formDataState.asStateFlow()

    protected val formData
        get() = formDataState.value

    fun updateFormData(function: (TData) -> TData) {
        _formDataState.update(function)
    }

    protected abstract fun sendForm()

    fun submitForm() {
        val error = formData.validate()
        if (error == null) {
            sendForm()
        } else {
            updateUiState { FormValidationErrorState(error) }
        }
    }
}