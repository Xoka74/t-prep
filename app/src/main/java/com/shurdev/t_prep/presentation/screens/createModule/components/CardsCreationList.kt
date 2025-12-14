package com.shurdev.t_prep.presentation.screens.createModule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.data.models.CardData

@Composable
fun CardsCreationList(
    cards: List<CardData>,
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
}