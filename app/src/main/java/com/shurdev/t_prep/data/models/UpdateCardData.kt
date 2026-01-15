package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName

data class UpdateCardData(
    @SerializedName("time_of_answer")
    val answerTime: String,
    @SerializedName("right_answer")
    val rightAnswer: Boolean
)
