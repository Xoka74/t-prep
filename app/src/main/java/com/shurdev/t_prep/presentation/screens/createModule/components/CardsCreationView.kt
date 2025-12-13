package com.shurdev.t_prep.presentation.screens.createModule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.R
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.presentation.components.buttons.SecondaryButton
import com.shurdev.t_prep.presentation.components.textFields.AppTextField

@Composable
fun CardsCreationView(
    cards: List<CardData>,
    onCardAdd: () -> Unit,
    onCardChange: (Int, CardData) -> Unit,
) {
    Text("Карточки")

    Column {
        cards.mapIndexed { index, card ->
            Column {
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hint = "Вопрос",
                    text = card.question,
                    onTextChange = {
                        onCardChange(index, card.copy(question = it))
                    },
                )
                Spacer(Modifier.height(8.dp))
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = card.answer,
                    hint = "Ответ",
                    onTextChange = {
                        onCardChange(index, card.copy(answer = it))
                    },
                )
            }
        }
    }


    SecondaryButton(
        "Добавить карточку",
        onClick = onCardAdd,
    )
}