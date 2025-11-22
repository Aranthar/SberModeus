package ru.sbermodeus.presentation.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.plus
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.SkillLevel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CoursesScreenViewModel @Inject constructor(

) : ViewModel() {
    val vmScope = viewModelScope + SupervisorJob()

    val mockCourses = listOf(
        Course(
            id = UUID.randomUUID(),
            name = "Основы Python",
            description = "Введение в программирование на Python, синтаксис, базовые структуры данных.",
            requiredSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Логика", 2)),
            givenSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Python", 3))
        ),
        Course(
            id = UUID.randomUUID(),
            name = "Анализ данных на SQL",
            description = "Обработка, анализ и запросы к данным с помощью SQL.",
            requiredSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Python", 2)),
            givenSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "SQL", 3))
        ),
        Course(
            id = UUID.randomUUID(),
            name = "Jetpack Compose",
            description = "Разработка интерфейса для Android с использованием современного подхода.",
            requiredSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "Kotlin", 2)),
            givenSkillLevels = listOf(SkillLevel(UUID.randomUUID(), "UI/UX", 3))
        )
    )

    private val _state = MutableStateFlow(
        CoursesState(
            coursesList = mockCourses
        )
    )
    val state: StateFlow<CoursesState> = _state.asStateFlow()


}