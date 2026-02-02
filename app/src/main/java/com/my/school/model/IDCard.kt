package com.my.school.model

data class IDCard(
    val studentId: String,
    val validFrom: String,
    val validUntil: String,
    val bloodGroup: String,
    val emergencyContact: String
)
