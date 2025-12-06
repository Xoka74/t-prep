package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("access_token")
    val accessToken: String,
)