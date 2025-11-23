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
            .background(MaterialTheme.colorScheme.background)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
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
    val grad = Brush.horizontalGradient(
        listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.10f),
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.30f)
        )
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(grad)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    text = "Семестр ${period.period}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 14.dp)
                )
                Spacer(Modifier.weight(1f))
                // Очки за семестр - playful badge
                ChipBadge(points = 100 * period.period)
            }
            Spacer(Modifier.height(14.dp))

            period.courses.forEach { course ->
                ModernRoadmapCourseChip(
                    course = course,
                    accent = getAccentColorForCourse(course)
                )
                Spacer(Modifier.height(10.dp))
            }

            // Прогресс по прохождению семестра
            val semesterProgress = period.courses.map { 1 }.sum().toFloat() / (period.courses.size * 1f)
            ProgressBarWithLabel(progress = semesterProgress)
        }
    }
}

@Composable
fun ModernRoadmapCourseChip(course: Course, accent: Color) {
    val animatedCardColor by animateColorAsState(
        targetValue = accent.copy(alpha = 0.15f),
        label = "animCardColor"
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = animatedCardColor
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = course.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = accent
                )
                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun ChipBadge(points: Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 14.dp, vertical = 7.dp)
    ) {
        Text(
            text = "★ $points XP",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun ProgressBarWithLabel(progress: Float) {
    Column(Modifier.padding(top = 9.dp)) {
        LinearProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(6.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.17f)
        )
        Text(
            text = "Прогресс семестра: ${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

fun getAccentColorForCourse(course: Course): Color {
    val accents = listOf(
        Color(0xFF5B44FF), // Primary Indigo
        Color(0xFF16CA98), // Mint
        Color(0xFFF4B600), // Yellow
        Color(0xFF6750A4), // Material M3 Primary
    )
    return accents[course.name.hashCode().absoluteValue % accents.size]
}
