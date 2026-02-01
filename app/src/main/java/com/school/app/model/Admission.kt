package com.school.app.model

data class Admission(
    val id: String,
    val applicantName: String,
    val applicantEmail: String,
    val appliedForClass: String,
    val status: AdmissionStatus,
    val applicationDate: String
)

enum class AdmissionStatus {
    PENDING, APPROVED, REJECTED
}
