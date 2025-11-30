package com.shurdev.domain.forms

abstract class Form<T : FormValidationError> {
    abstract fun validate(): T?
}