package ru.sbermodeus.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import ru.sbermodeus.domain.model.DemandLevel
import ru.sbermodeus.domain.model.SkillLevel
import ru.sbermodeus.domain.model.Specialization
import ru.sbermodeus.domain.model.User
import ru.sbermodeus.domain.repository.CacheRepository
import ru.sbermodeus.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val cacheRepository: CacheRepository,
) : ViewModel() {
    val vmScope = viewModelScope + SupervisorJob()

    val mockUser = User(
        id = UUID.randomUUID(),
        login = "student42",
        name = "Анна",
        surname = "Иванова",
        specialization = Specialization(
            id = UUID.randomUUID(),
            name = "Аналитик данных",
            description = "Специалист по обработке данных.",
            avgSalary = 145000.0,
            avgCandidates = 2.3,
            requiredSkills = listOf(
                SkillLevel(UUID.randomUUID(), "Python", 3),
                SkillLevel(UUID.randomUUID(), "SQL", 2),
            ),
            demandLevel = DemandLevel.HIGH
        ),
        activeCourses = emptyList(),
        completedCourses = emptyList(),
        skills = emptyList(),
    )

    val mockSkills = listOf(
        SkillLevel(UUID.randomUUID(), "Python", 3),
        SkillLevel(UUID.randomUUID(), "SQL", 2),
        SkillLevel(UUID.randomUUID(), "Математика", 1),
        SkillLevel(UUID.randomUUID(), "ML", 0)
    )

    private val _state = MutableStateFlow(ProfileState(
        user = mockUser,
        skillsList = mockSkills
    ))
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        vmScope.launch {
            val myId = cacheRepository.getMyId() ?: return@launch
            val me = userRepository.getUserById(id = myId) ?: return@launch
            _state.update { it.copy(skillsList = me.skills) }
        }
    }
}