package ua.smartwaste.kmp.core.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

fun Context.toast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_SHORT,
) {
    Toast.makeText(this, message, duration).show()
}

fun Context.checkPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}