package com.school.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.app.model.Student
import com.school.app.viewmodel.StudentViewModel

@Composable
fun StudentProfileScreen(
    student: Student,
    studentViewModel: StudentViewModel
) {
    val idCard by studentViewModel.idCard.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Profile", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Name: ${student.name}")
                Text("Admission No: ${student.admissionNumber}")
                Text("Class: ${student.standard} - ${student.section}")
                Text("Roll No: ${student.rollNumber}")
                Text("Email: ${student.email}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (idCard != null) {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                 Column(modifier = Modifier.padding(16.dp)) {
                     Text("Digital ID Card", style = MaterialTheme.typography.headlineSmall)
                     Spacer(modifier = Modifier.height(8.dp))
                     Text("Valid Until: ${idCard!!.validUntil}")
                     Text("Blood Group: ${idCard!!.bloodGroup}")
                     Text("Emergency: ${idCard!!.emergencyContact}")
                 }
            }
        }
    }
}
