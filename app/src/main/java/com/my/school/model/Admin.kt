package com.my.school.model

data class Admin(
    val id: String,
    val name: String,
    val email: String,
    val role: String = "Administrator"
)
