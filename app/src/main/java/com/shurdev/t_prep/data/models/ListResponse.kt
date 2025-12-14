package com.shurdev.t_prep.data.models

data class ListResponse<T>(
    val items: List<T>,
    val totalCount: Int,
)