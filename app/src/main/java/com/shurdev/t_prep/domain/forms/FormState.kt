package com.shurdev.t_prep.domain.forms

import com.shurdev.domain.forms.FormValidationError

sealed interface FormState

interface EditableState

data object FormPreparationState : FormState

data object FormPreparationFailedState : FormState

data class FormValidationErrorState<T : FormValidationError>(val error: T) : FormState,
    EditableState

data object FormEditingState : FormState, EditableState

data object FormSubmittingState : FormState

data object FormSubmittedState : FormState

data object FormSubmissionErrorState : FormState, EditableState