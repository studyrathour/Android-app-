package com.my.school.model

data class Result(
    val id: String,
    val studentId: String,
    val examName: String,
    val subject: String,
    val marksObtained: Int,
    val totalMarks: Int,
    val grade: String
)
