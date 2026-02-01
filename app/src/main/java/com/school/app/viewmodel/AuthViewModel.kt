package com.school.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.app.model.Admin
import com.school.app.model.Student
import com.school.app.repository.MockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loginStudent(email: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val student = MockRepository.loginStudent(email)
            if (student != null) {
                _authState.value = AuthState.StudentAuthenticated(student)
            } else {
                _error.value = "Invalid Student Email"
            }
            _loading.value = false
        }
    }

    fun loginAdmin(email: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val admin = MockRepository.loginAdmin(email)
            if (admin != null) {
                _authState.value = AuthState.AdminAuthenticated(admin)
            } else {
                _error.value = "Invalid Admin Email"
            }
            _loading.value = false
        }
    }

    fun logout() {
        _authState.value = AuthState.LoggedOut
    }
}

sealed class AuthState {
    object LoggedOut : AuthState()
    data class StudentAuthenticated(val student: Student) : AuthState()
    data class AdminAuthenticated(val admin: Admin) : AuthState()
}
