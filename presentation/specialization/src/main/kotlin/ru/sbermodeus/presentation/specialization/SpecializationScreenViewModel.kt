package ru.sbermodeus.presentation.specialization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.plus
import ru.sbermodeus.domain.model.DemandLevel
import ru.sbermodeus.domain.model.SkillLevel
import ru.sbermodeus.domain.model.Specialization
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SpecializationScreenViewModel @Inject constructor(

) : ViewModel() {
    val vmScope = viewModelScope + SupervisorJob()

    val mockSpecializations = listOf(
        Specialization(
            id = UUID.randomUUID(),
            name = "Аналитик данных",
            description = "Специалист, обрабатывающий и анализирующий большие объёмы информации.",
            avgSalary = 145000.0,
            avgCandidates = 2.4,
            requiredSkills = listOf(
                SkillLevel(UUID.randomUUID(), "Python", 4),
                SkillLevel(UUID.randomUUID(), "SQL", 5),
                SkillLevel(UUID.randomUUID(), "Математика", 3)
            ),
            demandLevel = DemandLevel.HIGH,
        ),
        Specialization(
            id = UUID.randomUUID(),
            name = "Разработчик приложений",
            description = "Создаёт мобильные и/или веб-приложения.",
            avgSalary = 125000.0,
            avgCandidates = 3.0,
            requiredSkills = listOf(
                SkillLevel(UUID.randomUUID(), "Kotlin", 5),
                SkillLevel(UUID.randomUUID(), "Jetpack Compose", 4),
                SkillLevel(UUID.randomUUID(), "UI/UX", 3)
            ),
            demandLevel = DemandLevel.MEDIUM,
        )
    )

    private val _state = MutableStateFlow(
        SpecializationState(
            specializationList = mockSpecializations
        )
    )
    val state: StateFlow<SpecializationState> = _state.asStateFlow()


}