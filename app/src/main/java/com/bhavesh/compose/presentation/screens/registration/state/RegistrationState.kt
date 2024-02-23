package com.bhavesh.compose.presentation.screens.registration.state

import android.net.Uri
import com.bhavesh.compose.presentation.state.ErrorState

/**
 * Registration State holding ui input values
 */
data class RegistrationState(
    val photo: Uri = Uri.EMPTY,
    val name: String = "",
    val emailId: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isRegistrationSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class RegistrationErrorState(
    val nameErrorState: ErrorState = ErrorState(),
    val emailIdErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)