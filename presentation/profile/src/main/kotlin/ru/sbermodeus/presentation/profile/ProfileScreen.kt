package ru.sbermodeus.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sbermodeus.domain.model.SkillLevel
import ru.sbermodeus.presentation.profile.components.SpecializationProgressBar

@Composable
fun ProfileScreen(
    onOpenSpecialization: () -> Unit,
    viewModel: ProfileScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "${state.user.name.firstOrNull() ?: ""}${state.user.surname.firstOrNull() ?: ""}".uppercase(),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "${state.user.name} ${state.user.surname}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(5.dp))
        state.user.specialization?.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        state.user.specialization?.requiredSkills?.map { it.level }?.average()?.div(5.0)?.let {
            SpecializationProgressBar(
                progress = it,
                onClick = onOpenSpecialization
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "🏅 Навыки и достижения",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(7.dp))
        ProfileSkillsGrid(skills = state.skillsList)
    }
}

@Composable
fun ProfileSkillsGrid(skills: List<SkillLevel>?) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        skills?.forEach { skill ->
            Box(Modifier.padding(end = 12.dp, bottom = 12.dp)) {
                SkillLevelBox(skill)
            }
        }
    }
}

@Composable
fun SkillLevelBox(skill: SkillLevel) {
    Box(
        Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                when {
                    skill.level >= 4 -> MaterialTheme.colorScheme.secondary
                    skill.level >= 2 -> MaterialTheme.colorScheme.primary
                    else -> Color(0xFFF4B600)
                }
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(7.dp))
            Text(
                text = skill.name,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
            Spacer(Modifier.width(7.dp))
            Text(
                text = "${skill.level}/5",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
            )
        }
    }
}
