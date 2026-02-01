package com.my.school.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.my.school.model.Student
import com.my.school.viewmodel.StudentViewModel

@Composable
fun StudentProfileScreen(
    student: Student,
    studentViewModel: StudentViewModel
) {
    val idCard by studentViewModel.idCard.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Image(
            painter = rememberAsyncImagePainter("https://picsum.photos/200/200"),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Student Details", style = MaterialTheme.typography.titleLarge)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("Name: ${student.name}", style = MaterialTheme.typography.bodyLarge)
                Text("Admission No: ${student.admissionNumber}", style = MaterialTheme.typography.bodyLarge)
                Text("Class: ${student.standard} - ${student.section}", style = MaterialTheme.typography.bodyLarge)
                Text("Roll No: ${student.rollNumber}", style = MaterialTheme.typography.bodyLarge)
                Text("Email: ${student.email}", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (idCard != null) {
            // ID Card UI
            Card(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF4A90E2), Color(0xFF003366))
                            )
                        )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "SCHOOL ID CARD",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                             Image(
                                painter = rememberAsyncImagePainter("https://picsum.photos/200/200"),
                                contentDescription = "Profile",
                                modifier = Modifier.size(80.dp).clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(student.name, style = MaterialTheme.typography.titleLarge, color = Color.White)
                                Text("Class: ${student.standard}-${student.section}", color = Color.White)
                                Text("Valid Until: ${idCard!!.validUntil}", color = Color.White)
                                Text("Blood Group: ${idCard!!.bloodGroup}", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}
