package ru.sbermodeus.presentation.specialization.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.sbermodeus.domain.model.DemandLevel
import ru.sbermodeus.domain.model.Specialization

@Composable
fun SpecializationInfo(
    specialization: Specialization,
    onConfirm: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 22.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = specialization.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = specialization.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 5
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlineInfoChip(
                icon = {
                    Text(
                        "₽",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                title = "Зарплата",
                value = "${specialization.avgSalary.toInt()}",
                color = MaterialTheme.colorScheme.primary
            )
            VerticalDivider()
            OutlineInfoChip(
                icon = { Text("👥", style = MaterialTheme.typography.titleMedium) },
                title = "Конкурс",
                value = "${specialization.avgCandidates}",
                color = MaterialTheme.colorScheme.primary
            )
            VerticalDivider()
            when (specialization.demandLevel) {
                DemandLevel.HIGH -> OutlineInfoChip(
                    icon = { Text("🔥", style = MaterialTheme.typography.titleMedium) },
                    title = "Востребована",
                    color = Color(0xFFF06292)
                )

                DemandLevel.MEDIUM -> OutlineInfoChip(
                    icon = { Text("🌤", style = MaterialTheme.typography.titleMedium) },
                    title = "Средний спрос",
                    color = Color(0xFF5B44FF)
                )

                DemandLevel.LOW -> OutlineInfoChip(
                    icon = { Text("💤", style = MaterialTheme.typography.titleMedium) },
                    title = "Спрос низкий",
                    color = Color(0xFFFFA726)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Ключевые навыки:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                specialization.requiredSkills.forEach { skill ->
                    Box(Modifier.padding(end = 10.dp, bottom = 6.dp)) {
                        SkillMiniChip(skill.name, skill.level)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Выбрать специальность",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun OutlineInfoChip(
    icon: @Composable () -> Unit,
    title: String,
    value: String? = null,
    color: Color
) {
    Column(
        modifier = Modifier
            .widthIn(min = 76.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.16f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
        value?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                color = color,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun VerticalDivider() {
    Box(
        Modifier
            .width(1.1.dp)
            .height(40.dp)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.07f))
    )
}

@Composable
fun SkillMiniChip(name: String, level: Int) {
    Box(
        Modifier
            .clip(RoundedCornerShape(40))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = "$name $level/5",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
