package com.my.school.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.school.ui.components.EventCard
import com.my.school.ui.components.NotificationItem
import com.my.school.viewmodel.StudentViewModel

@Composable
fun StudentHomeScreen(
    studentViewModel: StudentViewModel
) {
    val events by studentViewModel.events.collectAsState()
    val notifications by studentViewModel.notifications.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        item {
            Text("Notifications", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(notifications) { notification ->
            NotificationItem(notification)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Upcoming Events", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(events) { event ->
            EventCard(event)
        }
    }
}
