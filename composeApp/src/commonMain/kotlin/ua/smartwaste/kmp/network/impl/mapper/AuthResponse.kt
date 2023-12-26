package ua.smartwaste.kmp.network.impl.mapper

import ua.smartwaste.kmp.network.api.auth.model.AuthResponse
import ua.smartwaste.kmp.network.impl.auth.dto.AuthResponseDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

fun AuthResponseDto.toAuthResponse(): AuthResponse {
    return AuthResponse(
        accessToken = accessToken,
        code = code,
        message = message,
    )
}
