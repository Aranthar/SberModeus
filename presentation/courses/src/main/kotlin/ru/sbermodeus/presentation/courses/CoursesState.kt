package ru.sbermodeus.presentation.courses

import ru.sbermodeus.domain.model.Course

data class CoursesState(
    var coursesList: List<Course> = emptyList(),
)