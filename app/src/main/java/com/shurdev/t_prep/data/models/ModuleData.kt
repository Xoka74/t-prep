package com.shurdev.t_prep.data.models

import com.google.gson.annotations.SerializedName
import com.shurdev.t_prep.domain.models.AccessLevel

data class ModuleData(
    val name: String,
    val description: String,
    @SerializedName("ViewAccess")
    val viewAccess: AccessLevel,
    @SerializedName("EditAccess")
    val editAccess: AccessLevel,
    @SerializedName("PasswordHash")
    val passwordHash: String,
    @SerializedName("isIntervalRepetitionsEnabled")
    val isIntervalRepetitionsEnabled: Boolean
)