package com.shurdev.t_prep.presentation.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.models.StudySession
import com.shurdev.t_prep.domain.usecases.SaveSessionUseCase
import com.shurdev.t_prep.domain.usecases.StartQuizUseCase
import com.shurdev.t_prep.domain.usecases.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val startQuizUseCase: StartQuizUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(QuizState())
    val uiState: State<QuizState> = _uiState

    fun startQuiz(subjectId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val questions = startQuizUseCase(subjectId)
                _uiState.value = _uiState.value.copy(
                    cards = questions,
                    currentQuestionIndex = 0,
                    isLoading = false,
                    showResults = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun selectAnswer(selectedIndex: Int) {
        val currentState = _uiState.value
        val currentQuestion = currentState.cards[currentState.currentQuestionIndex]

        val isCorrect = selectedIndex == currentQuestion.correctAnswer
        val updatedScore = if (isCorrect) currentState.score + 1 else currentState.score

        _uiState.value = currentState.copy(
            selectedAnswer = selectedIndex,
            isAnswerCorrect = isCorrect,
            score = updatedScore
        )
    }

    fun nextQuestion() {
        val currentState = _uiState.value
        if (currentState.currentQuestionIndex < currentState.cards.size - 1) {
            _uiState.value = currentState.copy(
                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                selectedAnswer = null,
                isAnswerCorrect = null
            )
        } else {
            finishQuiz()
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            val currentQuestion = _uiState.value.currentCard
            currentQuestion?.let {
                toggleBookmarkUseCase(it.id)
            }
        }
    }

    private fun finishQuiz() {
        _uiState.value = _uiState.value.copy(showResults = true)

        viewModelScope.launch {
            val session = StudySession(
                id = UUID.randomUUID().toString(),
                subjectId = "subject_id",
                date = System.currentTimeMillis(),
                correctAnswers = _uiState.value.score,
                totalQuestions = _uiState.value.cards.size,
                timeSpentInMinutes = 30
            )
            saveSessionUseCase(session)
        }
    }
}