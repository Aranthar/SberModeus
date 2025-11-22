package ru.sbermodeus.presentation.specialization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SpecializationScreenViewModel @Inject constructor(

): ViewModel() {
    val vmScope = viewModelScope + SupervisorJob()

    private val _state = MutableStateFlow(
        SpecializationState()
    )
    val state: StateFlow<SpecializationState> = _state.asStateFlow()


}