package ua.smartwaste.kmp.presentation.screens.profile

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class ProfileState(
    val username: String,
    val email: String,
)

val emptyProfileState = ProfileState(
    username = "",
    email = "",
)
