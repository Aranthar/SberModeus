package ru.sbermodeus.presentation.specialization

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.sbermodeus.data.model.dto.SpecializationDTO
import ru.sbermodeus.presentation.specialization.components.SpecializationCard
import ru.sbermodeus.presentation.specialization.components.SpecializationInfo
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecializationScreen(
    onSpecializationClick: (id: UUID) -> Unit = {},
    viewModel: SpecializationScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var selectedSpecialization by remember { mutableStateOf<SpecializationDTO?>(null) }
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.specializationList, key = { it.id }) { specialization ->
            SpecializationCard(
                specialization = specialization,
                isSelected = specialization == selectedSpecialization,
                onClick = {
                    selectedSpecialization = specialization
                    coroutineScope.launch { bottomSheetState.show() }
                }
            )
        }
    }

    if (selectedSpecialization != null) {
        ModalBottomSheet (
            onDismissRequest = {
                coroutineScope.launch { bottomSheetState.hide() }
                selectedSpecialization = null
            },
            sheetState = bottomSheetState
        ) {
            SpecializationInfo(
                specialization = selectedSpecialization!!,
                onConfirm = {
                    onSpecializationClick(selectedSpecialization!!.id)
                    coroutineScope.launch { bottomSheetState.hide() }
                    selectedSpecialization = null
                }
            )
        }
    }
}

@Preview
@Composable
fun previewScreen(){
    SpecializationScreen()
}