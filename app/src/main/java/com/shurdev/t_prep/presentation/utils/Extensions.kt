package com.shurdev.t_prep.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun <T> T.useDebounce(
    delayMillis: Long,
    onChange: (T) -> Unit
): T {
    val state by rememberUpdatedState(this)

    var isFirstRun by remember { mutableStateOf(true) }

    LaunchedEffect(state) {
        if (isFirstRun) {
            isFirstRun = false
        } else {
            delay(delayMillis)
            onChange(state)
        }
    }

    return state
}