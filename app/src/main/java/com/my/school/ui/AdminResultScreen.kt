package com.my.school.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.my.school.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminResultScreen(navController: NavController, viewModel: AdminViewModel) {
    var studentId by remember { mutableStateOf("") }
    var examName by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var marks by remember { mutableStateOf("") }
    var totalMarks by remember { mutableStateOf("100") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Upload Result") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                label = { Text("Student ID") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = examName,
                onValueChange = { examName = it },
                label = { Text("Exam Name (e.g., Mid-Term)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("Subject") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = marks,
                onValueChange = { marks = it },
                label = { Text("Marks Obtained") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = totalMarks,
                onValueChange = { totalMarks = it },
                label = { Text("Total Marks") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (studentId.isNotBlank() && examName.isNotBlank() && marks.isNotBlank()) {
                        viewModel.addResult(
                            studentId,
                            examName,
                            subject,
                            marks.toIntOrNull() ?: 0,
                            totalMarks.toIntOrNull() ?: 100
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Upload Result")
            }
        }
    }
}
