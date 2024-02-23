package com.bhavesh.compose.presentation.screens.profilescreen

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bhavesh.compose.presentation.state.BackButtonHandler
import com.bhavesh.compose.presentation.state.getActivity
import com.bhavesh.compose.presentation.ui.composableviews.TitleText
import com.bhavesh.compose.presentation.ui.theme.AppThemeDimens

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val profileState = navController.previousBackStackEntry?.savedStateHandle?.get<ProfileState>("profile_state")

    val context = LocalContext.current
    val activity = context.getActivity()
    BackButtonHandler { activity?.finish() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {

        if (profileState == null) {
            // Error message
            Text(
                modifier = Modifier.padding(top = AppThemeDimens.dimens.paddingLarge),
                text = "Data not available"
            )
            return
        }

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
                    .align(Alignment.CenterHorizontally)
            ) {

                // Heading
                TitleText(
                    modifier = Modifier.padding(top = AppThemeDimens.dimens.paddingLarge),
                    text = "Hey, ${profileState.name}"
                )

                if (profileState.photo != Uri.EMPTY) {
                    AsyncImage(
                        model = profileState.photo,
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .padding(12.dp)
                            .align(Alignment.CenterHorizontally),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = profileState.email,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = profileState.name,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}