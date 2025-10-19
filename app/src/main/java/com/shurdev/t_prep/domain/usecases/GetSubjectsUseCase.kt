package com.shurdev.t_prep.domain.usecases


import com.shurdev.t_prep.domain.models.Subject
import com.shurdev.t_prep.domain.repositories.SubjectRepository

class GetSubjectsUseCase(
    private val repository: SubjectRepository
) {
    suspend operator fun invoke(): List<Subject> {
        return repository.getSubjects()
    }
}
