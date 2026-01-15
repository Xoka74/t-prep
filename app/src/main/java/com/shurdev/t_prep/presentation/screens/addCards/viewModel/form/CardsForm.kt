package com.shurdev.t_prep.presentation.screens.addCards.viewModel.form

import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.domain.forms.Form

data class CardsForm(
    val cards: List<CardData> = listOf()
) : Form<CardsFormValidationError>() {
    override fun validate(): CardsFormValidationError? {
        return null
    }
}