package com.school.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.app.model.*
import com.school.app.repository.MockRepository
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
}
