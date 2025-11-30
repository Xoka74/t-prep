package com.shurdev.t_prep.presentation.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shurdev.t_prep.data.models.MeUserDto
import com.shurdev.t_prep.domain.models.MeUser

@Composable
fun ProfileHeader(
    user: MeUser,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://avatar.iran.liara.run/public/15")
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = user.name,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}