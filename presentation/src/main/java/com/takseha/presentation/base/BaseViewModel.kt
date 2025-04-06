package com.takseha.presentation.base

import androidx.lifecycle.ViewModel
import com.takseha.domain.usecase.SaveErrorLogUseCase
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var saveErrorLogUseCase: SaveErrorLogUseCase
}