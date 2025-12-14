package com.shurdev.t_prep.presentation.screens.createModule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.presentation.components.textFields.AppTextField

@Composable
fun CardCreationView(
    index: Int,
    card: CardData,
    onCardChange: (Int, CardData) -> Unit,
    onCardRemove: (Int) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Карточка ${index + 1}")
                Spacer(Modifier.weight(1f))
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = ""
                        )
                    },
                    onClick = {
                        onCardRemove(index)
                    }
                )
            }

            Spacer(Modifier.height(8.dp))

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = card.question,
                hint = stringResource(R.string.question),
                onTextChange = {
                    onCardChange(index, card.copy(question = it))
                },
            )

            Spacer(Modifier.height(8.dp))

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = card.answer,
                hint = stringResource(R.string.answer),
                onTextChange = {
                    onCardChange(index, card.copy(answer = it))
                },
            )
        }
    }
}