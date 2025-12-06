package com.shurdev.t_prep.data.managers

import com.shurdev.t_prep.BuildConfig
import com.shurdev.t_prep.data.models.Config

object ConfigManager {
    fun getConfig() = Config(
        baseUrl = BuildConfig.baseUrl,
        serverClientId = BuildConfig.serverClientId
    )
}