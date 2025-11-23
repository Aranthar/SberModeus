package ru.sbermodeus.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import ru.sbermodeus.domain.repository.CacheRepository
import ru.sbermodeus.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val cacheRepository: CacheRepository,
): ViewModel() {
    val vmScope = viewModelScope + SupervisorJob()

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateSurname(surname: String) {
        _state.value = _state.value.copy(surname = surname)
    }

    fun onLoginClick(name: String, surname: String) {
        vmScope.launch {
            val me = userRepository.createUser(name = name, surname = surname) ?: return@launch
            cacheRepository.setMyId(id = me.id)
        }
    }
}