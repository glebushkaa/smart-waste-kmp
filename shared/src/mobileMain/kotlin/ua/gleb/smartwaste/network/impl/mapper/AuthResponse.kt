package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.auth.model.AuthResponse
import ua.gleb.smartwaste.network.auth.dto.AuthResponseDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

fun AuthResponseDto.toAuthResponse(): ua.gleb.smartwaste.network.api.auth.model.AuthResponse {
    return AuthResponse(
        accessToken = accessToken,
        code = code,
        message = message,
    )
}
