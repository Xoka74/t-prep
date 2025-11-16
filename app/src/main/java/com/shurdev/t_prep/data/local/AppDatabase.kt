package com.shurdev.t_prep.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shurdev.t_prep.data.local.dao.CardDao
import com.shurdev.t_prep.data.local.dao.StudySessionDao
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.local.entities.CardEntity
import com.shurdev.t_prep.data.local.entities.StudySessionEntity
import com.shurdev.t_prep.data.local.entities.ModuleEntity

@Database(
    entities = [ModuleEntity::class, CardEntity::class, StudySessionEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moduleDao(): ModuleDao
    abstract fun cardDao(): CardDao
    abstract fun studySessionDao(): StudySessionDao
}