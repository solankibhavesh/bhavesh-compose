package com.bhavesh.compose.presentation.screens.profilescreen

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileState(val name: String, val email: String, val photo: Uri = Uri.EMPTY) : Parcelable