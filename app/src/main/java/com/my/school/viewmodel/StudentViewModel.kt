package com.my.school.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.school.model.*
import com.my.school.repository.MockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val _fees = MutableStateFlow<List<Fee>>(emptyList())
    val fees: StateFlow<List<Fee>> = _fees.asStateFlow()

    private val _results = MutableStateFlow<List<Result>>(emptyList())
    val results: StateFlow<List<Result>> = _results.asStateFlow()

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications.asStateFlow()

    private val _idCard = MutableStateFlow<IDCard?>(null)
    val idCard: StateFlow<IDCard?> = _idCard.asStateFlow()

    fun loadStudentData(studentId: String) {
        viewModelScope.launch {
            _fees.value = MockRepository.getStudentFees(studentId)
            _results.value = MockRepository.getStudentResults(studentId)
            _events.value = MockRepository.getEvents()
            _notifications.value = MockRepository.getNotifications(TargetAudience.STUDENT)
            _idCard.value = MockRepository.getIDCard(studentId)
        }
    }
}
