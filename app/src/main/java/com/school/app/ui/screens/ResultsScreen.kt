package com.school.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.app.viewmodel.StudentViewModel

@Composable
fun ResultsScreen(studentViewModel: StudentViewModel) {
    val results by studentViewModel.results.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
             Text("Exam Results", style = MaterialTheme.typography.headlineMedium)
             Spacer(modifier = Modifier.height(16.dp))
        }
        items(results) { result ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(result.examName, style = MaterialTheme.typography.titleMedium)
                    Text("Subject: ${result.subject}")
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Marks: ${result.marksObtained}/${result.totalMarks}")
                        Text("Grade: ${result.grade}", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}
