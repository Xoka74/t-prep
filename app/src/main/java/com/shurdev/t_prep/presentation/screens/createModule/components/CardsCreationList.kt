package com.shurdev.t_prep.presentation.screens.createModule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.presentation.components.buttons.SecondaryButton

@Composable
fun CardsCreationList(
    cards: List<CardData>,
    onCardAdd: () -> Unit,
    onCardChange: (Int, CardData) -> Unit,
    onCardRemove: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        cards.mapIndexed { index, card ->
            CardCreationView(
                index = index,
                card = card,
                onCardChange = onCardChange,
                onCardRemove = onCardRemove,
            )
        }
    }

    Spacer(Modifier.height(8.dp))

    SecondaryButton(
        "Добавить карточку",
        onClick = onCardAdd,
    )
}