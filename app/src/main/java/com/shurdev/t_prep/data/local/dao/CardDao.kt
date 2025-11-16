package com.shurdev.t_prep.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.shurdev.t_prep.data.local.entities.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM cards WHERE moduleId = :moduleId")
    suspend fun getCardsByModule(moduleId: String): List<CardEntity>

    @Query("UPDATE cards SET isBookmarked = NOT isBookmarked WHERE id = :cardId")
    suspend fun toggleBookmark(cardId: String)

    @Query("SELECT * FROM cards WHERE isBookmarked = 1")
    suspend fun getBookmarkedCards(): List<CardEntity>
}