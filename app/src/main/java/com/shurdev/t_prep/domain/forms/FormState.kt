package com.shurdev.domain.forms

sealed interface FormState

interface EditableState

data object FormPreparationState : FormState

data object FormPreparationFailedState : FormState

data class FormValidationErrorState<T : FormValidationError>(val error: T) : FormState,
    EditableState

data object FormEditingState : FormState, EditableState

data object FormSubmittingState : FormState

data class FormSubmittedState<T>(val data: T) : FormState

data object FormSubmissionErrorState : FormState, EditableState