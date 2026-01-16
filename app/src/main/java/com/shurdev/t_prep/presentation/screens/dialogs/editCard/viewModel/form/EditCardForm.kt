package com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form

import com.shurdev.t_prep.domain.forms.Form
import com.shurdev.domain.forms.ValidationError
import com.shurdev.t_prep.data.models.CardData

data class EditCardForm(
    val initialCardData: CardData = CardData("", ""),
    val cardData: CardData = CardData("", ""),
) : Form<EditCardValidationError>() {
    override fun validate(): EditCardValidationError? {
        val answerError = if (cardData.answer.isEmpty()) ValidationError.Required else null
        val questionError = if (cardData.question.isEmpty()) ValidationError.Required else null

        if (listOfNotNull(answerError, questionError).any()) {
            return EditCardValidationError(
                answerError = answerError,
                questionError = questionError,
            )
        }

        return null
    }
}