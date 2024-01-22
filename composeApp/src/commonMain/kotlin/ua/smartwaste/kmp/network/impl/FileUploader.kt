package ua.smartwaste.kmp.network.impl

import io.ktor.http.content.PartData

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

expect class FileUploader {

    fun convertFileToPartData(path: String): List<PartData>
}