package com.my.school.model

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val date: String,
    val targetAudience: TargetAudience
)

enum class TargetAudience {
    STUDENT, ADMIN, ALL
}
