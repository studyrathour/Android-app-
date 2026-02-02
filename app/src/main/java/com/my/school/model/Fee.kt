package com.my.school.model

data class Fee(
    val id: String,
    val studentId: String,
    val title: String,
    val amount: Double,
    val dueDate: String,
    val status: FeeStatus,
    val paymentDate: String? = null
)

enum class FeeStatus {
    PAID, DUE, OVERDUE
}
