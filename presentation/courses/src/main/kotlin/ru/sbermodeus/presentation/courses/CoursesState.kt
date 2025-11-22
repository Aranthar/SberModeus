package ru.sbermodeus.presentation.courses

import ru.sbermodeus.domain.model.Course
import java.util.UUID

data class CoursesState(
    var coursesList: List<Course> = emptyList(),
    var selectedIds: List<UUID> = emptyList()
)