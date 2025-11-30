package com.shurdev.t_prep.presentation.components.loaders

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.shurdev.t_prep.presentation.components.layout.Center

@Composable
fun Loader() {
    Center {
        CircularProgressIndicator()
    }
}