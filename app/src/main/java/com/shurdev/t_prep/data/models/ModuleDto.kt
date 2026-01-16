package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName
import com.shurdev.t_prep.domain.models.AccessLevel
import com.shurdev.t_prep.domain.models.Card

data class ModuleDto(
    val id: Int,
    val ownerId: Int,
    val name: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    @SerializedName("ViewAccess")
    val viewAccess: AccessLevel,
    @SerializedName("EditAccess")
    val editAccess: AccessLevel,
    val cards: List<Card>? = null,
    @SerializedName("IsIntervalRepetitionsEnabled")
    val isIntervalRepetitionsEnabled: Boolean,
    @SerializedName("CardsToRepeatCount")
    val cardsToRepeatCount: Int,
    @SerializedName("TotalCards")
    val totalCards: Int
)