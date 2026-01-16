package com.shurdev.t_prep.presentation.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.models.MeUser

@Composable
fun ProfileHeader(
    user: MeUser,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = ImageVector.vectorResource(R.drawable.icon_profile),
            contentDescription = null,

        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = user.name,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}