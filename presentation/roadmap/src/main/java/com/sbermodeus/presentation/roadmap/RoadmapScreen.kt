package com.sbermodeus.presentation.roadmap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sbermodeus.domain.model.Course
import ru.sbermodeus.domain.model.RoadmapPeriod

@Composable
fun RoadmapScreen(
    viewModel: RoadmapScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        state.roadmap.periods.forEach { period ->
            item {
                RoadmapPeriodBlock(period = period)
            }
        }
    }
}

@Composable
fun RoadmapPeriodBlock(period: RoadmapPeriod) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.22f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {
        Text(
            text = "Семестр ${period.period}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(Modifier.height(12.dp))
        period.courses.forEach { course ->
            CourseMiniCard(course = course)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun CourseMiniCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = course.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
