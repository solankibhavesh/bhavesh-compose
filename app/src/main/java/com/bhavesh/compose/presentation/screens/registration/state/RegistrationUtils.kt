package com.bhavesh.compose.presentation.screens.registration.state

import com.bhavesh.compose.R
import com.bhavesh.compose.presentation.state.ErrorState

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.profile_error_msg_empty_email
)

val emailValidErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.profile_error_msg_valid_email
)

val nameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.profile_error_msg_empty_name
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.profile_error_msg_empty_password
)

val confirmPasswordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.profile_error_msg_empty_confirm_password
)

val passwordMismatchErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.profile_error_msg_password_mismatch
)
