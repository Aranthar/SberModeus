package ru.sbermodeus.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = { newValue ->
                    viewModel.updateName(newValue)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Имя") },
                singleLine = true
            )

            OutlinedTextField(
                value = state.surname,
                onValueChange = { newValue ->
                    viewModel.updateSurname(newValue)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Фамилия") },
                singleLine = true
            )

            Button(
                onClick = { onLoginClick(state.name, state.surname) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.name.isNotBlank() && state.surname.isNotBlank()
            ) {
                Text(text = "Войти")
            }
        }
    }
}

@Preview
@Composable
fun previewScreen(){
    AuthScreen()
}