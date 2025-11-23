package com.sbermodeus.presentation.roadmap

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.RoadmapPeriod
import kotlin.math.absoluteValue

@Composable
fun RoadmapScreen(
    viewModel: RoadmapScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        state.roadmap.periods.forEach { period ->
            item {
                ModernRoadmapPeriodBlock(period = period)
            }
        }
    }
}

@Composable
fun ModernRoadmapPeriodBlock(period: RoadmapPeriod) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            Modifier
                .width(5.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topEnd = 6.dp, bottomEnd = 6.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(Modifier.width(9.dp))
        // Весь контент семестра
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier
                        .size(26.dp)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            CircleShape
                        )
                        .padding(4.dp)
                )
                Text(
                    text = "Семестр ${period.period}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(Modifier.weight(1f))
                ChipBadge(points = 100 * period.period)
            }
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                period.courses.forEach { course ->
                    ModernRoadmapCourseChip(
                        course = course,
                        accent = getAccentColorForCourse(course)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            ProgressBarWithLabel(progress = 1f) // Пример. Замените "1f" на реальный прогресс
        }
    }
}

@Composable
fun ModernRoadmapCourseChip(course: Course, accent: Color) {
    val animatedCardColor by animateColorAsState(
        targetValue = accent.copy(alpha = 0.11f),
        label = "animCardColor"
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = animatedCardColor
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 6.dp)
                .heightIn(min = 56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp, horizontal = 0.dp)
            ) {
                Text(
                    text = course.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = accent
                )
                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
            }
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = accent,
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .size(23.dp)
            )
        }
    }
}

@Composable
fun ChipBadge(points: Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = "★ $points XP",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun ProgressBarWithLabel(progress: Float) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 7.dp)
    ) {
        LinearProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(5.5.dp)
                .clip(RoundedCornerShape(6.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = "Прогресс: ${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

fun getAccentColorForCourse(course: Course): Color {
    val accents = listOf(
        Color(0xFF5B44FF),
        Color(0xFF16CA98),
        Color(0xFFF4B600),
        Color(0xFF6750A4),
        Color(0xFF4FBAF6),
        Color(0xFFEC5766),
    )
    return accents[course.name.hashCode().absoluteValue % accents.size]
}
