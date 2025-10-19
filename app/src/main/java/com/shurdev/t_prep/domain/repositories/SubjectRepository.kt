package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.Subject

interface SubjectRepository {
    suspend fun getSubjects(): List<Subject>
    suspend fun getSubjectById(id: String): Subject?
    suspend fun updateSubjectProgress(subjectId: String, completed: Int)
}