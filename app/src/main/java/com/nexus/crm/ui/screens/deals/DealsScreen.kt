package com.nexus.crm.ui.screens.deals

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexus.crm.data.model.Deal
import com.nexus.crm.data.model.DealStage
import com.nexus.crm.ui.components.DealListItem
import com.nexus.crm.ui.components.FilterChip
import com.nexus.crm.ui.components.NexusFAB
import com.nexus.crm.ui.theme.DarkBackground
import com.nexus.crm.ui.theme.SupabaseGreen
import com.nexus.crm.ui.theme.MidGray
import com.nexus.crm.ui.theme.OffWhite
import com.nexus.crm.ui.theme.LightGray
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealsScreen(
    viewModel: DealsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    Scaffold(
        floatingActionButton = {
            NexusFAB(onClick = { showAddDialog = true })
        },
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Deals",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = OffWhite
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBackground
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.stages.forEachIndexed { index, stage ->
                    FilterChip(
                        label = stage.name.replace("_", " "),
                        selected = stage == uiState.selectedStage,
                        onClick = { viewModel.onStageSelected(stage) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (uiState.deals.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "No deals in ${uiState.selectedStage.name.replace("_", " ")}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MidGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Total: ${currencyFormat.format(uiState.totalValue)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = SupabaseGreen
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "Total: ${currencyFormat.format(uiState.totalValue)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = SupabaseGreen,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(uiState.deals) { deal ->
                    DealListItem(
                        deal = deal,
                        onClick = { }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddDealDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { deal ->
                showAddDialog = false
            }
        )
    }
}

@Composable
fun AddDealDialog(
    onDismiss: () -> Unit,
    onConfirm: (Deal) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var contactName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = DarkBackground,
        title = {
            Text(
                text = "New Deal",
                color = OffWhite
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Deal Title") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SupabaseGreen,
                        unfocusedBorderColor = MidGray,
                        focusedTextColor = OffWhite,
                        unfocusedTextColor = OffWhite
                    )
                )
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Value ($)") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SupabaseGreen,
                        unfocusedBorderColor = MidGray,
                        focusedTextColor = OffWhite,
                        unfocusedTextColor = OffWhite
                    )
                )
                OutlinedTextField(
                    value = contactName,
                    onValueChange = { contactName = it },
                    label = { Text("Contact Name") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SupabaseGreen,
                        unfocusedBorderColor = MidGray,
                        focusedTextColor = OffWhite,
                        unfocusedTextColor = OffWhite
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val deal = Deal(
                        title = title.ifEmpty { "New Deal" },
                        value = value.toDoubleOrNull() ?: 0.0,
                        contactName = contactName,
                        stage = DealStage.APPOINTMENT
                    )
                    onConfirm(deal)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SupabaseGreen
                )
            ) {
                Text("Add", color = DarkBackground)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = MidGray)
            }
        }
    )
}