package com.my.school.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.school.model.*
import com.my.school.repository.MockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdminViewModel : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _admissions = MutableStateFlow<List<Admission>>(emptyList())
    val admissions: StateFlow<List<Admission>> = _admissions.asStateFlow()

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    fun loadAdminData() {
        viewModelScope.launch {
            _students.value = MockRepository.getAllStudents()
            _admissions.value = MockRepository.getAdmissions()
            _events.value = MockRepository.getEvents()
        }
    }

    fun addEvent(title: String, description: String, date: String, time: String, location: String) {
        viewModelScope.launch {
            val newEvent = Event(
                id = System.currentTimeMillis().toString(),
                title = title,
                description = description,
                date = date,
                time = time,
                location = location
            )
            MockRepository.addEvent(newEvent)
            _events.value = MockRepository.getEvents()
        }
    }

    fun approveAdmission(admissionId: String) {
        viewModelScope.launch {
            MockRepository.updateAdmissionStatus(admissionId, AdmissionStatus.APPROVED)
            _admissions.value = MockRepository.getAdmissions()
        }
    }

    fun rejectAdmission(admissionId: String) {
        viewModelScope.launch {
            MockRepository.updateAdmissionStatus(admissionId, AdmissionStatus.REJECTED)
            _admissions.value = MockRepository.getAdmissions()
        }
    }

    fun sendNotification(title: String, message: String, target: TargetAudience) {
        viewModelScope.launch {
            val notification = Notification(
                id = System.currentTimeMillis().toString(),
                title = title,
                message = message,
                date = "2024-02-02", // Mock date
                targetAudience = target
            )
            MockRepository.sendNotification(notification)
        }
    }

    fun addFee(studentId: String, title: String, amount: Double, dueDate: String) {
        viewModelScope.launch {
            val fee = Fee(
                id = System.currentTimeMillis().toString(),
                studentId = studentId,
                title = title,
                amount = amount,
                dueDate = dueDate,
                status = FeeStatus.DUE
            )
            MockRepository.addFee(fee)
        }
    }

    fun addResult(studentId: String, examName: String, subject: String, marksObtained: Int, totalMarks: Int) {
        viewModelScope.launch {
            val percentage = (marksObtained.toDouble() / totalMarks.toDouble()) * 100
            val grade = when {
                percentage >= 90 -> "A+"
                percentage >= 80 -> "A"
                percentage >= 70 -> "B"
                percentage >= 60 -> "C"
                else -> "F"
            }
            val result = Result(
                id = System.currentTimeMillis().toString(),
                studentId = studentId,
                examName = examName,
                subject = subject,
                marksObtained = marksObtained,
                totalMarks = totalMarks,
                grade = grade
            )
            MockRepository.addResult(result)
        }
    }
}
