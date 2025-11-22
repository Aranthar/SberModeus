package ru.sbermodeus.presentation.navigation.bottom_bar

import androidx.compose.ui.graphics.Color
import ru.sbermodeus.presentation.navigation.R

sealed class BottomBarTab(val title: String, val icon: Int, val color: Color) {
    data object Courses : BottomBarTab(
        title = "Courses",
        icon = R.drawable.ic_specialization,
        color = Color.White
    )
    data object Roadmap : BottomBarTab(
        title = "Roadmap",
        icon = R.drawable.ic_traectory,
        color = Color.White
    )
    data object Profile : BottomBarTab(
        title = "Profile",
        icon = R.drawable.ic_profile,
        color = Color.White
    )
}

val tabs = listOf(
    BottomBarTab.Courses,
    BottomBarTab.Roadmap,
    BottomBarTab.Profile,
)