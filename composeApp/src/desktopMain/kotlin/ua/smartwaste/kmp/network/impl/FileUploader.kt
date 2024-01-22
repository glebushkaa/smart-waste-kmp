package ua.smartwaste.kmp.network.impl

import io.ktor.http.content.PartData

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

actual class FileUploader {

    actual fun convertFileToPartData(path: String): List<PartData> {
        return emptyList()
    }
}