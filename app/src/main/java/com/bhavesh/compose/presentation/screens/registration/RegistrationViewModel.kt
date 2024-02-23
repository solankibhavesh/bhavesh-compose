package com.bhavesh.compose.presentation.screens.registration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bhavesh.compose.presentation.screens.registration.state.RegistrationErrorState
import com.bhavesh.compose.presentation.screens.registration.state.RegistrationState
import com.bhavesh.compose.presentation.screens.registration.state.RegistrationUiEvent
import com.bhavesh.compose.presentation.screens.registration.state.confirmPasswordEmptyErrorState
import com.bhavesh.compose.presentation.screens.registration.state.emailEmptyErrorState
import com.bhavesh.compose.presentation.screens.registration.state.emailValidErrorState
import com.bhavesh.compose.presentation.screens.registration.state.nameEmptyErrorState
import com.bhavesh.compose.presentation.screens.registration.state.passwordEmptyErrorState
import com.bhavesh.compose.presentation.screens.registration.state.passwordMismatchErrorState
import com.bhavesh.compose.presentation.state.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {

    var registrationState = mutableStateOf(RegistrationState())
        private set

    /**
     * Function called on any login event [RegistrationUiEvent]
     */
    fun onUiEvent(registrationUiEvent: RegistrationUiEvent) {
        when (registrationUiEvent) {

            // Image Uploaded
            is RegistrationUiEvent.ImageUploaded -> {
                registrationState.value = registrationState.value.copy(photo = registrationUiEvent.value)
            }

            // Name changed event
            is RegistrationUiEvent.NameChanged -> {
                registrationState.value = registrationState.value.copy(
                    name = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        nameErrorState = if (registrationUiEvent.inputValue.trim().isEmpty()) {
                            nameEmptyErrorState
                        } else {
                            ErrorState()
                        }
                    )
                )
            }

            // Email id changed event
            is RegistrationUiEvent.EmailChanged -> {
                registrationState.value = registrationState.value.copy(
                    emailId = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        emailIdErrorState = if (registrationUiEvent.inputValue.trim().isEmpty()) {
                            // Email id empty state
                            emailEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }

            // Password changed event
            is RegistrationUiEvent.PasswordChanged -> {
                registrationState.value = registrationState.value.copy(
                    password = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        passwordErrorState = if (registrationUiEvent.inputValue.trim().isEmpty()) {
                            // Password Empty state
                            passwordEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }

            // Confirm Password changed event
            is RegistrationUiEvent.ConfirmPasswordChanged -> {
                registrationState.value = registrationState.value.copy(
                    confirmPassword = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        confirmPasswordErrorState = when {

                            // Empty state of confirm password
                            registrationUiEvent.inputValue.trim().isEmpty() -> {
                                confirmPasswordEmptyErrorState
                            }

                            // Password is different than the confirm password
                            registrationState.value.password.trim() != registrationUiEvent.inputValue -> {
                                passwordMismatchErrorState
                            }

                            // Valid state
                            else -> ErrorState()
                        }
                    )
                )
            }

            // Submit Registration event
            is RegistrationUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    registrationState.value = registrationState.value.copy(isRegistrationSuccessful = true)
                }
            }
        }
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (usecase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val nameString = registrationState.value.name.trim()
        val emailString = registrationState.value.emailId.trim()
        val passwordString = registrationState.value.password.trim()
        val confirmPasswordString = registrationState.value.confirmPassword.trim()

        return when {
            // Email empty
            nameString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(nameErrorState = nameEmptyErrorState)
                )
                false
            }

            // Email empty
            emailString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(emailIdErrorState = emailEmptyErrorState)
                )
                false
            }



                // Valid Email empty
            !android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(emailIdErrorState = emailValidErrorState)
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(passwordErrorState = passwordEmptyErrorState)
                )
                false
            }

            //Confirm Password Empty
            confirmPasswordString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(confirmPasswordErrorState = confirmPasswordEmptyErrorState)
                )
                false
            }

            // Password and Confirm Password are different
            passwordString != confirmPasswordString -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(confirmPasswordErrorState = passwordMismatchErrorState)
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                registrationState.value =
                    registrationState.value.copy(errorState = RegistrationErrorState())
                true
            }
        }
    }
}