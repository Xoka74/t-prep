package com.shurdev.t_prep.presentation.components.layout

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermissionLauncher() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

    val permissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    val isGranted = permissionState.status.isGranted

    if (!isGranted) {


        if (permissionState.status.shouldShowRationale) {
            // TODO: Show Rationale
            return
        }
        else LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }
    }
}