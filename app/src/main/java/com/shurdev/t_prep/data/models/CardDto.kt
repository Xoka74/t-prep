package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName

data class CardDto(
    val id: String,
    val question: String,
    @SerializedName("answer_variant")
    val answerVariant : List<String>,
    @SerializedName("right_answer")
    val rightAnswer: Int,
)