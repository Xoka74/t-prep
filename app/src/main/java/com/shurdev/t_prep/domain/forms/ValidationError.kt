package com.shurdev.domain.forms

enum class ValidationError {
    Required,
    TooLong,
    TooShort,
    Invalid;

    fun toErrorString(
        required: String? = null,
        tooLong: String? = null,
        tooShort: String? = null,
        invalid: String? = null,
    ): String? = when (this) {
        Required -> required
        TooLong -> tooLong
        TooShort -> tooShort
        Invalid -> invalid
    }
}