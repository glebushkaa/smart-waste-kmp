package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.auth.model.AuthResponse
import ua.gleb.smartwaste.network.auth.response.TokenResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

fun TokenResponse.toAuthResponse(): AuthResponse {
    return AuthResponse(
        accessToken = token,
    )
}
