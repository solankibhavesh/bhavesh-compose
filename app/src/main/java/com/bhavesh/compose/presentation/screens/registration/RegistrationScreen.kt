package com.bhavesh.compose.presentation.screens.registration

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bhavesh.compose.R
import com.bhavesh.compose.presentation.screens.profilescreen.ProfileState
import com.bhavesh.compose.presentation.screens.registration.state.RegistrationUiEvent
import com.bhavesh.compose.presentation.state.Screen
import com.bhavesh.compose.presentation.ui.composableviews.TitleText
import com.bhavesh.compose.presentation.ui.theme.AppThemeDimens

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
    navController: NavController
) {

    val registrationState by remember {
        registrationViewModel.registrationState
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { registrationViewModel.onUiEvent(RegistrationUiEvent.ImageUploaded(it)) } }
    )

    if (registrationState.isRegistrationSuccessful) {
        LaunchedEffect(key1 = true) {
            val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
            savedStateHandle?.set("profile_state", ProfileState(registrationState.name, registrationState.emailId, registrationState.photo))
            navController.navigate(Screen.Profile.route)
        }
    } else {
        // Full Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {

            // Main card Content for Registration
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppThemeDimens.dimens.paddingLarge)
            ) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = AppThemeDimens.dimens.paddingLarge)
                        .padding(bottom = AppThemeDimens.dimens.paddingExtraLarge)
                ) {

                    // Heading Registration
                    TitleText(
                        modifier = Modifier.padding(top = AppThemeDimens.dimens.paddingLarge),
                        text = stringResource(id = R.string.profile_heading_text)
                    )

                    /**
                     * Registration Inputs Composable
                     */
                    RegistrationInputs(
                        registrationState = registrationState,
                        onAddImage = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        onNameChange = { registrationViewModel.onUiEvent(RegistrationUiEvent.NameChanged(it)) },
                        onEmailIdChange = { registrationViewModel.onUiEvent(RegistrationUiEvent.EmailChanged(it)) },
                        onPasswordChange = { registrationViewModel.onUiEvent(RegistrationUiEvent.PasswordChanged(it)) },
                        onConfirmPasswordChange = { registrationViewModel.onUiEvent(RegistrationUiEvent.ConfirmPasswordChanged(it)) }
                    ) {
                        registrationViewModel.onUiEvent(RegistrationUiEvent.Submit)
                    }
                }
            }
        }
    }
}