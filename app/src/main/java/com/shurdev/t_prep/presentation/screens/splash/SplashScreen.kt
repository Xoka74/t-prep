package com.shurdev.t_prep.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.layout.Center

@Composable
fun SplashScreen() {
    Center {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.app_icon),
            contentDescription = stringResource(R.string.start_screen),
            modifier = Modifier.size(200.dp, 150.dp)
        )
    }
}