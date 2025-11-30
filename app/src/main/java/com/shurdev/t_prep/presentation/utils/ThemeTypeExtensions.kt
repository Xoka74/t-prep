package com.shurdev.t_prep.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.models.ThemeType

@Composable
fun ThemeType.toResString(): String {
    val id = when (this) {
        ThemeType.System -> R.string.system
        ThemeType.Light -> R.string.light
        ThemeType.Dark -> R.string.dark
    }

    return stringResource(id)
}