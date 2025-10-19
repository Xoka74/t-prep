package com.shurdev.t_prep.presentation.screens.subjects;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.usecases.GetSubjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectsViewModel @Inject constructor(
    private val getSubjectsUseCase: GetSubjectsUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(SubjectsState())
    val uiState: State<SubjectsState> = _uiState

    init {
        loadSubjects()
    }

    fun loadSubjects() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val subjects = getSubjectsUseCase()
                _uiState.value = _uiState.value.copy(
                    subjects = subjects,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}