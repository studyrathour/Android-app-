package com.school.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.app.model.AdmissionStatus
import com.school.app.viewmodel.AdminViewModel

@Composable
fun AdminDashboardScreen(adminViewModel: AdminViewModel) {
    val students by adminViewModel.students.collectAsState()
    val admissions by adminViewModel.admissions.collectAsState()

    var showAddEventDialog by remember { mutableStateOf(false) }

    if (showAddEventDialog) {
        AddEventDialog(
            onDismiss = { showAddEventDialog = false },
            onAddEvent = { title, desc, date, time, loc ->
                adminViewModel.addEvent(title, desc, date, time, loc)
                showAddEventDialog = false
            }
        )
    }

    // Simple Tabs or just a list of features
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Admin Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showAddEventDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Add New Event")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Admission Requests", style = MaterialTheme.typography.titleLarge)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(admissions) { admission ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(admission.applicantName, style = MaterialTheme.typography.titleMedium)
                        Text("Class: ${admission.appliedForClass}")
                        Text("Status: ${admission.status}")
                        if (admission.status == AdmissionStatus.PENDING) {
                            Row {
                                Button(onClick = { adminViewModel.approveAdmission(admission.id) }) {
                                    Text("Approve")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(onClick = { adminViewModel.rejectAdmission(admission.id) }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                                    Text("Reject")
                                }
                            }
                        }
                    }
                }
            }
        }

        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        Text("Total Students: ${students.size}", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Manage Students", style = MaterialTheme.typography.titleMedium)
        LazyColumn(modifier = Modifier.weight(1f)) {
             items(students) { student ->
                 Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                     Column(modifier = Modifier.padding(8.dp)) {
                         Text(student.name, style = MaterialTheme.typography.bodyLarge)
                         Text("Class: ${student.standard} - ${student.section}")
                     }
                 }
             }
        }
    }
}

@Composable
fun AddEventDialog(onDismiss: () -> Unit, onAddEvent: (String, String, String, String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Event") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date (YYYY-MM-DD)") })
                OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") })
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
            }
        },
        confirmButton = {
            Button(onClick = { onAddEvent(title, description, date, time, location) }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
