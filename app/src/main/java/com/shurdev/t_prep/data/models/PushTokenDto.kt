package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName

data class PushTokenDto(
    @SerializedName("push_token")
    val pushToken: String
)