package ru.sbermodeus.presentation.profile

import ru.sbermodeus.domain.model.SkillLevel
import ru.sbermodeus.domain.model.User

data class ProfileState(
    val user: User,
    val skillsList: List<SkillLevel>?,
)