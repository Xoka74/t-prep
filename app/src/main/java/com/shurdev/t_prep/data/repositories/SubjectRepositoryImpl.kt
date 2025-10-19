package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.local.dao.SubjectDao
import com.shurdev.t_prep.domain.models.Subject
import com.shurdev.t_prep.domain.repositories.SubjectRepository

class SubjectRepositoryImpl(
    private val subjectDao: SubjectDao,
) : SubjectRepository {

    override suspend fun getSubjects(): List<Subject> {
        return (1..10)
            .map {
                Subject(
                    id = "id_$it",
                    name = "name_$it",
                    description = "description_$it",
                    totalQuestions = it,
                    completedQuestions = it
                )
            }
    }

    override suspend fun getSubjectById(id: String): Subject? {
        return null
    }

    override suspend fun updateSubjectProgress(subjectId: String, completed: Int) {
        val subject = subjectDao.getSubjectById(subjectId)
        subject?.let {
            subjectDao.updateSubject(it.copy(completedQuestions = completed))
        }
    }
}