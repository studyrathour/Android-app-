package com.school.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.school.app.model.FeeStatus
import com.school.app.viewmodel.StudentViewModel

@Composable
fun FeesScreen(studentViewModel: StudentViewModel) {
    val fees by studentViewModel.fees.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
             Text("Fee Details", style = MaterialTheme.typography.headlineMedium)
             Spacer(modifier = Modifier.height(16.dp))
        }
        items(fees) { fee ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(fee.title, style = MaterialTheme.typography.titleMedium)
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Amount: $${fee.amount}")
                        Text(
                            text = fee.status.name,
                            color = when(fee.status) {
                                FeeStatus.PAID -> Color.Green
                                FeeStatus.DUE -> Color.Yellow
                                FeeStatus.OVERDUE -> Color.Red
                            }
                        )
                    }
                    Text("Due Date: ${fee.dueDate}")
                }
            }
        }
    }
}
