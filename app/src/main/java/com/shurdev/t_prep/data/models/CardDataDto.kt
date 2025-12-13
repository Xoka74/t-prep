package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName

data class CardDataDto(
    val question: String,
    val answer: String,
    @SerializedName("module_id")
    val moduleId: Int,
)