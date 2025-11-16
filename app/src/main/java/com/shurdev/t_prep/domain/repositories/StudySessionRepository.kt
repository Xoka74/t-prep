package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.StudySession
import com.shurdev.t_prep.domain.models.StudyStatistics

interface StudySessionRepository {
    suspend fun saveSession(session: StudySession)
    suspend fun getSessionsByModule(moduleId: String): List<StudySession>
    suspend fun getStatistics(): StudyStatistics
}