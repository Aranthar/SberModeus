package ru.sbermodeus.presentation.specialization.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sbermodeus.domain.model.Specialization

@Composable
fun SpecializationInfo(
    specialization: Specialization,
    onConfirm: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = specialization.name, style = MaterialTheme.typography.titleLarge)
        Text(text = specialization.description, style = MaterialTheme.typography.bodyLarge)

        Button(onClick = onConfirm, modifier = Modifier.fillMaxWidth()) {
            Text("Выбрать специальность")
        }
    }
}
