package com.shurdev.t_prep.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shurdev.t_prep.data.local.entities.SubjectEntity

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects")
    suspend fun getSubjects(): List<SubjectEntity>

    @Query("SELECT * FROM subjects WHERE id = :id")
    suspend fun getSubjectById(id: String): SubjectEntity?

    @Update
    suspend fun updateSubject(subject: SubjectEntity)

    @Insert
    suspend fun insertSubjects(subjects: List<SubjectEntity>)
}