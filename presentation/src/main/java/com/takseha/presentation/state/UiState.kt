package com.takseha.presentation.state

import com.takseha.domain.model.Failure

sealed class UiState<out T>(open val data: T? = null) {
    companion object {
        val initial = None
    }

    data object None : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Error<out T>(
        val alertMsg: String?,
        val error: Failure?,
        override val data: T? = null
    ) : UiState<T>(data)
    data class Success<out T>(override val data: T) : UiState<T>(data)
}