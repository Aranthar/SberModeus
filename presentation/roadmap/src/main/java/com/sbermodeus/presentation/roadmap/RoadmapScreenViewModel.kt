package com.sbermodeus.presentation.roadmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.plus
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.Roadmap
import ru.sbermodeus.domain.model.RoadmapPeriod
import ru.sbermodeus.domain.model.SkillLevel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RoadmapScreenViewModel @Inject constructor(

) : ViewModel() {
    val vmScope = viewModelScope + SupervisorJob()

    val mockRoadmap = Roadmap(
        periods = listOf(
            RoadmapPeriod(
                period = 1,
                courses = listOf(
                    Course(
                        id = UUID.randomUUID(),
                        name = "Основы математики",
                        description = "Базовые понятия алгебры и анализа.",
                        requiredSkillLevels = listOf(),
                        givenSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Математика", 2))
                    ),
                    Course(
                        id = UUID.randomUUID(),
                        name = "Введение в программирование",
                        description = "Изучение синтаксиса языков и базовых конструкций.",
                        requiredSkillLevels = listOf(),
                        givenSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Python", 1))
                    )
                )
            ),
            RoadmapPeriod(
                period = 2,
                courses = listOf(
                    Course(
                        id = UUID.randomUUID(),
                        name = "Анализ данных на Python",
                        description = "Работа с библиотеками NumPy, Pandas.",
                        requiredSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Python", 1)),
                        givenSkillLevels = listOf(
                            SkillLevel(UUID.randomUUID(), "Python", 2),
                            SkillLevel(UUID.randomUUID(), "Data Analysis", 2)
                        )
                    ),
                    Course(
                        id = UUID.randomUUID(),
                        name = "SQL и базы данных",
                        description = "Основы работы с реляционными базами.",
                        requiredSkillLevels = listOf(),
                        givenSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "SQL", 2))
                    )
                )
            )
        )
    )

    private val _state = MutableStateFlow(RoadmapState(
        roadmap = mockRoadmap
    ))
    val state: StateFlow<RoadmapState> = _state.asStateFlow()

}