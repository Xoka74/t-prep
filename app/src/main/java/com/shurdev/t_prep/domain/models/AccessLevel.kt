package com.shurdev.t_prep.domain.models

import com.google.gson.annotations.SerializedName

enum class AccessLevel {
    @SerializedName("all_users")
    AllUsers,
    @SerializedName("only_me")
    OnlyMe,
}