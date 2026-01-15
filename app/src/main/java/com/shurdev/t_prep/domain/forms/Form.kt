package com.shurdev.t_prep.domain.forms

import com.shurdev.domain.forms.FormValidationError

abstract class Form<T : FormValidationError> {
    abstract fun validate(): T?
}