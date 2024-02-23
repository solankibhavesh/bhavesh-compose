package com.bhavesh.compose.presentation.screens.registration.state

import android.net.Uri

/**
 * Registration Screen Events
 */
sealed class RegistrationUiEvent {
    data class ImageUploaded(val value: Uri) : RegistrationUiEvent()
    data class NameChanged(val inputValue: String) : RegistrationUiEvent()
    data class EmailChanged(val inputValue: String) : RegistrationUiEvent()
    data class PasswordChanged(val inputValue: String) : RegistrationUiEvent()
    data class ConfirmPasswordChanged(val inputValue: String) : RegistrationUiEvent()
    object Submit : RegistrationUiEvent()
}