package ru.sbermodeus.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.sbermodeus.domain.model.SkillLevel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        item {
            Text(
                text = "${state.user.name} ${state.user.surname}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Специализация: ${state.user.specialization?.name}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = state.user.specialization?.description?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(10.dp))

            // Прогресс по специализации (например, средний навык по входным скиллам)
            val progress = state.user.specialization?.requiredSkills?.map { it.level }?.average()
                ?.div(5.0)
            SpecializationProgressBar(progress ?: 0.0)
        }
        item {
            Spacer(Modifier.height(24.dp))
            Text("Навыки", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            SkillsRowBox(state.skillsList)
        }
    }
}

@Composable
fun SpecializationProgressBar(progress: Double) {
    Column {
        Text("Прогресс специализации", style = MaterialTheme.typography.labelMedium)
        Spacer(Modifier.height(7.dp))
        LinearProgressIndicator(
            progress = progress.toFloat().coerceIn(0f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.align(Alignment.End),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SkillsRowBox(skills: List<SkillLevel>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        skills.forEach { skill ->
            SkillLevelBox(skill)
        }
    }
}

@Composable
fun SkillLevelBox(skill: SkillLevel) {
    Box(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = skill.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = "${skill.level}/5",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
