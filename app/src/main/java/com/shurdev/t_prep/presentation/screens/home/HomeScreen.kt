package com.shurdev.t_prep.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout

@Composable
fun HomeScreen() {
    DefaultScreenLayout(
        title = stringResource(R.string.home),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Пока что пусто")
    }
}