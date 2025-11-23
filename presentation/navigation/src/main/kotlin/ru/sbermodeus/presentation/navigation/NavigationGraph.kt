package ru.sbermodeus.presentation.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sbermodeus.presentation.roadmap.RoadmapScreen
import ru.sbermodeus.presentation.auth.AuthScreen
import ru.sbermodeus.presentation.courses.CoursesScreen
import ru.sbermodeus.presentation.courses.CoursesScreenViewModel
import ru.sbermodeus.presentation.navigation.bottom_bar.AnimatedBottomBar
import ru.sbermodeus.presentation.navigation.bottom_bar.BottomBarTab
import ru.sbermodeus.presentation.navigation.bottom_bar.tabs
import ru.sbermodeus.presentation.profile.ProfileScreen
import ru.sbermodeus.presentation.specialization.SpecializationScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<Any>(AuthRoute) }

    val top = backStack.lastOrNull()
    val showBottomBar = remember(top) { isBottomBarDestination(top) }
    val selectedTabIndex by remember(top) {
        mutableIntStateOf(tabForKey(top)?.let { t -> tabs.indexOfFirst { it::class == t::class } }
            .takeIf { it != -1 } ?: 0
        )
    }

    val animatedSelectedTabIndex by animateFloatAsState(
        targetValue = selectedTabIndex.toFloat(),
        label = "animatedSelectedTabIndex",
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioLowBouncy
        )
    )
    val animatedColor by animateColorAsState(
        targetValue = tabs[selectedTabIndex].color,
        label = "animatedColor",
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    val viewModel: CoursesScreenViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                AnimatedBottomBar(
                    selectedTabIndex = selectedTabIndex,
                    animatedTabIndex = animatedSelectedTabIndex,
                    animatedColor = animatedColor,
                    onTabSelected = { tab, _ ->
                        navigateToTab(tab, backStack)
                    }
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavDisplay(
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            modifier = Modifier.padding(innerPadding),
            entryProvider = { key ->
                when (key) {
                    is SpecializationRoute -> NavEntry(key) {
                        SpecializationScreen(
                            onNavigateToCourses = {
                                backStack.add(CoursesRoute)
                            }
                        )
                    }
                    is AuthRoute -> NavEntry(key) {
                        AuthScreen(
                            onLoginClick = { backStack.add(SpecializationRoute) },
                        )
                    }
                    is CoursesRoute -> NavEntry(key) {
                        CoursesScreen(viewModel)
                    }
                    is RoadmapRoute -> NavEntry(key) {
                        RoadmapScreen()
                    }
                    is ProfileRoute -> NavEntry(key){
                        ProfileScreen(
                            onOpenSpecialization = {
                                backStack.add(SpecializationRoute)
                            }
                        )
                    }

                    else -> NavEntry(Unit) { Text("Unknown route") }
                }
            }
        )
    }
}

private fun isBottomBarDestination(key: Any?): Boolean =
    when (key) {
        is RoadmapRoute, is CoursesRoute, is ProfileRoute -> true
        else -> false
    }

private fun tabForKey(key: Any?): BottomBarTab? =
    when (key) {
        is RoadmapRoute -> BottomBarTab.Roadmap
        is CoursesRoute -> BottomBarTab.Courses
        is ProfileRoute -> BottomBarTab.Profile
        else -> null
    }

private fun navigateToTab(tab: BottomBarTab, backStack: SnapshotStateList<Any>) {
    when (tab) {
        BottomBarTab.Roadmap -> replaceWithRoot(backStack, RoadmapRoute)
        BottomBarTab.Courses -> replaceWithRoot(backStack, CoursesRoute)
        BottomBarTab.Profile -> replaceWithRoot(backStack, ProfileRoute)
    }
}

private fun replaceWithRoot(backStack: SnapshotStateList<Any>, root: Any) {
    backStack.clear()
    backStack.add(root)
}
