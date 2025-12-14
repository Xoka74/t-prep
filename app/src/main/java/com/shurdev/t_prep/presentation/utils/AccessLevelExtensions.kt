package com.shurdev.t_prep.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.models.AccessLevel

@Composable
fun AccessLevel.toResString(): String {
    val id = when (this) {
        AccessLevel.AllUsers -> R.string.all_users
        AccessLevel.OnlyMe -> R.string.only_me
    }

    return stringResource(id)
}