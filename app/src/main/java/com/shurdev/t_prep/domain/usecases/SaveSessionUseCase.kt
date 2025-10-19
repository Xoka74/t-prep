package com.shurdev.t_prep.domain.usecases

import com.shurdev.t_prep.domain.models.StudySession
import com.shurdev.t_prep.domain.repositories.StudySessionRepository

class SaveSessionUseCase(
    private val sessionRepository: StudySessionRepository
) {
    suspend operator fun invoke(session: StudySession) {
        sessionRepository.saveSession(session)
    }
}