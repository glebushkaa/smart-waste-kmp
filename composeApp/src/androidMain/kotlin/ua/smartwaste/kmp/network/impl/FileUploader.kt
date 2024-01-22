package ua.smartwaste.kmp.network.impl

import android.content.Context
import androidx.core.net.toUri
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData
import java.io.File
import java.io.FileOutputStream

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

actual class FileUploader(
    private val context: Context
) {

    actual fun convertFileToPartData(path: String): List<PartData> {
        val contentResolver = context.contentResolver
        val uri = path.toUri()
        val inputStream = contentResolver.openInputStream(uri)
        val file = File.createTempFile("upload", "tmp", context.cacheDir)
        inputStream?.use { input ->
            FileOutputStream(file).use { output -> input.copyTo(output) }
        }
        return formData {
            append(
                key = "Photo",
                value = file.readBytes(),
                headers = Headers.build {
                    append(HttpHeaders.ContentType, "image/*")
                    append(HttpHeaders.ContentDisposition, "filename=\"${file.name}.png\"")
                }
            )
        }
    }
}