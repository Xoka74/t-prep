package com.shurdev.t_prep.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shurdev.t_prep.data.local.dao.QuestionDao
import com.shurdev.t_prep.data.local.dao.StudySessionDao
import com.shurdev.t_prep.data.local.dao.SubjectDao
import com.shurdev.t_prep.data.local.entities.QuestionEntity
import com.shurdev.t_prep.data.local.entities.StudySessionEntity
import com.shurdev.t_prep.data.local.entities.SubjectEntity

@Database(
    entities = [SubjectEntity::class, QuestionEntity::class, StudySessionEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    abstract fun questionDao(): QuestionDao
    abstract fun studySessionDao(): StudySessionDao
}