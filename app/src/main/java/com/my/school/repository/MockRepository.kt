package com.my.school.repository

import com.my.school.model.*
import kotlinx.coroutines.delay

object MockRepository {

    // Mock Data
    private val students = mutableListOf(
        Student("1", "John Doe", "john@school.com", "ADM001", "10", "A", "101", "2005-05-15", "123 Main St", "Robert Doe", "555-0101"),
        Student("2", "Jane Smith", "jane@school.com", "ADM002", "10", "B", "102", "2006-08-20", "456 Oak Ave", "Mary Smith", "555-0102")
    )

    private val admins = mutableListOf(
        Admin("1", "Admin User", "admin@school.com")
    )

    private val notifications = mutableListOf(
        Notification("1", "Welcome", "Welcome to the new school year!", "2023-09-01", TargetAudience.ALL),
        Notification("2", "Exam Schedule", "Mid-term exams start next week.", "2023-10-15", TargetAudience.STUDENT),
        Notification("3", "Staff Meeting", "Meeting in the conference room.", "2023-10-20", TargetAudience.ADMIN)
    )

    private val events = mutableListOf(
        Event("1", "Annual Sports Day", "Sports activities for all students.", "2023-11-10", "09:00 AM", "School Ground"),
        Event("2", "Science Fair", "Showcasing science projects.", "2023-12-05", "10:00 AM", "Auditorium")
    )

    private val fees = mutableListOf(
        Fee("1", "1", "Term 1 Fee", 5000.0, "2023-09-01", FeeStatus.PAID, "2023-08-25"),
        Fee("2", "1", "Term 2 Fee", 5000.0, "2024-01-01", FeeStatus.DUE),
        Fee("3", "2", "Term 1 Fee", 5000.0, "2023-09-01", FeeStatus.OVERDUE)
    )

    private val results = mutableListOf(
        Result("1", "1", "Mid-Term", "Mathematics", 85, 100, "A"),
        Result("2", "1", "Mid-Term", "Science", 90, 100, "A+"),
        Result("3", "1", "Mid-Term", "English", 78, 100, "B+"),
        Result("4", "2", "Mid-Term", "Mathematics", 60, 100, "C")
    )

    private val admissions = mutableListOf(
        Admission("1", "New Kid", "newkid@email.com", "9", AdmissionStatus.PENDING, "2023-11-01")
    )

    // Simulation of delay
    private const val DELAY_MS = 500L

    // Auth
    suspend fun loginStudent(email: String): Student? {
        delay(DELAY_MS)
        return students.find { it.email == email }
    }

    suspend fun loginAdmin(email: String): Admin? {
        delay(DELAY_MS)
        return admins.find { it.email == email }
    }

    // Student Operations
    suspend fun getStudent(id: String): Student? {
        delay(DELAY_MS)
        return students.find { it.id == id }
    }

    suspend fun getStudentFees(studentId: String): List<Fee> {
        delay(DELAY_MS)
        return fees.filter { it.studentId == studentId }
    }

    suspend fun getStudentResults(studentId: String): List<Result> {
        delay(DELAY_MS)
        return results.filter { it.studentId == studentId }
    }

    suspend fun getNotifications(role: TargetAudience): List<Notification> {
        delay(DELAY_MS)
        return notifications.filter { it.targetAudience == role || it.targetAudience == TargetAudience.ALL }
    }

    suspend fun getEvents(): List<Event> {
        delay(DELAY_MS)
        return events
    }

    suspend fun getIDCard(studentId: String): IDCard? {
         delay(DELAY_MS)
         // Mock generation of ID card data
         val student = students.find { it.id == studentId } ?: return null
         return IDCard(
             studentId = student.id,
             validFrom = "2023-04-01",
             validUntil = "2024-03-31",
             bloodGroup = "O+",
             emergencyContact = student.parentPhone
         )
    }

    // Admin Operations
    suspend fun getAllStudents(): List<Student> {
        delay(DELAY_MS)
        return students
    }

    suspend fun addEvent(event: Event) {
        delay(DELAY_MS)
        events.add(event)
    }

    suspend fun sendNotification(notification: Notification) {
        delay(DELAY_MS)
        notifications.add(notification)
    }

    suspend fun getAdmissions(): List<Admission> {
        delay(DELAY_MS)
        return admissions
    }

    suspend fun updateAdmissionStatus(id: String, status: AdmissionStatus) {
        delay(DELAY_MS)
        val index = admissions.indexOfFirst { it.id == id }
        if (index != -1) {
            admissions[index] = admissions[index].copy(status = status)
        }
    }

    suspend fun addMockStudent(student: Student) {
        students.add(student)
    }
}
