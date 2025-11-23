package ru.sbermodeus.presentation.specialization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.sbermodeus.domain.model.Specialization
import ru.sbermodeus.presentation.specialization.components.SpecializationCard
import ru.sbermodeus.presentation.specialization.components.SpecializationInfo
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecializationScreen(
    onSpecializationSelect: (id: UUID) -> Unit = {},
    onNavigateToCourses: () -> Unit = {},
    viewModel: SpecializationScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    state.confirmedSpecialization?.let {
                        onSpecializationSelect(it.id)
                        onNavigateToCourses()
                    }
                },
                enabled = state.confirmedSpecialization != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Далее",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.specializationList, key = { it.id }) { specialization ->
                SpecializationCard(
                    specialization = specialization,
                    isSelected = specialization == state.confirmedSpecialization,
                    onClick = {
                        viewModel.selectSpecialization(specialization)
                        coroutineScope.launch { bottomSheetState.show() }
                    }
                )
            }
        }

        state.selectedSpecialization?.let { specialization ->
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.dismissSheet()
                    coroutineScope.launch { bottomSheetState.hide() }
                },
                sheetState = bottomSheetState
            ) {
                SpecializationInfo(
                    specialization = specialization,
                    onConfirm = {
                        viewModel.confirmSpecialization()
                        coroutineScope.launch { bottomSheetState.hide() }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    SpecializationScreen()
}