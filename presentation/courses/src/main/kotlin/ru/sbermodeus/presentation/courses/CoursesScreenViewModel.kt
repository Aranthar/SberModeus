package ru.sbermodeus.presentation.courses

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
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.SkillLevel
import ru.sbermodeus.domain.repository.CourseRepository
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CoursesScreenViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
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

    init {
        getData()
    }

    private fun getData() {
        vmScope.launch {
            val courses = courseRepository.getAllCourses() ?: return@launch
            _state.update { it.copy(coursesList = courses) }
        }
    }

    fun onToggleCourse(courseId: UUID) {
        _state.value = _state.value.copy(
            selectedIds = if (courseId in _state.value.selectedIds)
                _state.value.selectedIds - courseId
            else
                _state.value.selectedIds + courseId
        )
    }
}