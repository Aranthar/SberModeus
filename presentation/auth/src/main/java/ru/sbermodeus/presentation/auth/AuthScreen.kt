package ru.sbermodeus.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun AuthScreen(
    onLoginClick: (name: String, surname: String) -> Unit = { _, _ -> },
    viewModel: AuthScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Добро пожаловать!",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Твой путь к магистратуре начинается здесь ✨",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.updateName(it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Имя") },
                singleLine = true,
                shape = RoundedCornerShape(20.dp)
            )
            OutlinedTextField(
                value = state.surname,
                onValueChange = { viewModel.updateSurname(it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Фамилия") },
                singleLine = true,
                shape = RoundedCornerShape(20.dp)
            )

            Button(
                onClick = { onLoginClick(state.name, state.surname) },
                enabled = state.name.isNotBlank() && state.surname.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Войти",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
