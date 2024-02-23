package com.bhavesh.compose.presentation.screens.registration

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bhavesh.compose.R
import com.bhavesh.compose.presentation.screens.registration.state.RegistrationState
import com.bhavesh.compose.presentation.ui.composableviews.EmailTextField
import com.bhavesh.compose.presentation.ui.composableviews.NameTextField
import com.bhavesh.compose.presentation.ui.composableviews.NormalButton
import com.bhavesh.compose.presentation.ui.composableviews.PasswordTextField
import com.bhavesh.compose.presentation.ui.theme.AppThemeDimens
import com.bhavesh.compose.presentation.ui.theme.PurpleGrey80

@Composable
fun RegistrationInputs(
    registrationState: RegistrationState,
    onAddImage: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    // Login Inputs Section
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppThemeDimens.dimens.paddingSmall)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .border(width = 2.dp, color = PurpleGrey80, shape = RoundedCornerShape(5.dp))
                .width(200.dp)
                .height(150.dp)
                .clickable(true, onClick = onAddImage)

        ) {
            // Photo picker
            if (registrationState.photo == Uri.EMPTY) {
                Text(
                    text = "Add Image",
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            } else {
                AsyncImage(
                    model = registrationState.photo,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

        }

        // Name ID
        NameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppThemeDimens.dimens.paddingLarge),
            value = registrationState.name,
            onValueChange = onNameChange,
            label = stringResource(id = R.string.profile_name_label),
            isError = registrationState.errorState.nameErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next

        )

        // Email ID
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppThemeDimens.dimens.paddingLarge),
            value = registrationState.emailId,
            onValueChange = onEmailIdChange,
            label = stringResource(id = R.string.profile_email_label),
            isError = registrationState.errorState.emailIdErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.emailIdErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppThemeDimens.dimens.paddingLarge),
            value = registrationState.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.profile_password_label),
            isError = registrationState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.passwordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        // Confirm Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppThemeDimens.dimens.paddingLarge),
            value = registrationState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = stringResource(id = R.string.profile_confirm_password_label),
            isError = registrationState.errorState.confirmPasswordErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.confirmPasswordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Done
        )

        // Registration Submit Button
        NormalButton(
            modifier = Modifier.padding(top = AppThemeDimens.dimens.paddingExtraLarge),
            text = stringResource(id = R.string.profile_button_text),
            onClick = onSubmit
        )

    }
}