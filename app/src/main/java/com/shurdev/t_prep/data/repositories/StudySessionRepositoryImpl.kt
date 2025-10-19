package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.domain.models.StudySession
import com.shurdev.t_prep.domain.models.StudyStatistics
import com.shurdev.t_prep.domain.repositories.StudySessionRepository

class StudySessionRepositoryImpl: StudySessionRepository {
    override suspend fun saveSession(session: StudySession) {
    }

    override suspend fun getSessionsBySubject(subjectId: String): List<StudySession> {
        return emptyList()
    }

    override suspend fun getStatistics(): StudyStatistics {
        TODO()
    }
}