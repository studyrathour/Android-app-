package com.my.school.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.my.school.ui.components.SchoolAppTopBar
import com.my.school.ui.components.StudentBottomBar
import com.my.school.ui.screens.*
import com.my.school.viewmodel.AdminViewModel
import com.my.school.viewmodel.AuthState
import com.my.school.viewmodel.AuthViewModel
import com.my.school.viewmodel.StudentViewModel

@Composable
fun SchoolAppNavigation(
    authViewModel: AuthViewModel = viewModel(),
    studentViewModel: StudentViewModel = viewModel(),
    adminViewModel: AdminViewModel = viewModel()
) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "login"

    // Effect to handle navigation based on auth state
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.StudentAuthenticated -> {
                val student = (authState as AuthState.StudentAuthenticated).student
                studentViewModel.loadStudentData(student.id)
                navController.navigate("student_home") {
                    popUpTo("login") { inclusive = true }
                }
            }
            is AuthState.AdminAuthenticated -> {
                adminViewModel.loadAdminData()
                navController.navigate("admin_dashboard") {
                    popUpTo("login") { inclusive = true }
                }
            }
            AuthState.LoggedOut -> {
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            if (currentRoute != "login") {
                val title = when (currentRoute) {
                    "student_home" -> "Dashboard"
                    "student_profile" -> "Profile"
                    "student_fees" -> "Fees"
                    "student_results" -> "Results"
                    "admin_dashboard" -> "Admin Dashboard"
                    else -> "School App"
                }
                SchoolAppTopBar(
                    title = title,
                    canNavigateBack = false, // Handle back navigation if needed
                    logout = { authViewModel.logout() }
                )
            }
        },
        bottomBar = {
            if (authState is AuthState.StudentAuthenticated) {
                StudentBottomBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo("student_home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(authViewModel = authViewModel)
            }

            // Student Routes
            composable("student_home") {
                StudentHomeScreen(studentViewModel = studentViewModel)
            }
            composable("student_profile") {
                val student = (authState as? AuthState.StudentAuthenticated)?.student
                if (student != null) {
                    StudentProfileScreen(student = student, studentViewModel = studentViewModel)
                }
            }
            composable("student_fees") {
                FeesScreen(studentViewModel = studentViewModel)
            }
            composable("student_results") {
                ResultsScreen(studentViewModel = studentViewModel)
            }

            // Admin Routes
            composable("admin_dashboard") {
                AdminDashboardScreen(adminViewModel = adminViewModel)
            }
        }
    }
}
