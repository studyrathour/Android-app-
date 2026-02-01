package com.my.school.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            // Banner
            Box(modifier = Modifier.fillMaxWidth().height(180.dp)) {
                Image(
                    painter = rememberAsyncImagePainter("https://picsum.photos/800/400?grayscale"),
                    contentDescription = "School Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Welcome Back!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(androidx.compose.ui.Alignment.BottomStart).padding(16.dp)
                )
            }
        }

        item {
             Column(modifier = Modifier.padding(16.dp)) {
                Text("Announcements", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
             }
        }
        items(notifications) { notification ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                NotificationItem(notification)
            }
        }
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Upcoming Events", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        items(events) { event ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                EventCard(event)
            }
        }
    }
}
