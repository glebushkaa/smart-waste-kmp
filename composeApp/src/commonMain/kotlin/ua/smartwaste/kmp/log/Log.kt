package ua.smartwaste.kmp.log

import io.github.aakira.napier.Napier

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

fun error(tag: String, error: Throwable) {
    Napier.e(message = "", throwable = error, tag = tag)
}

inline fun error(tag: String, logMessage: () -> String) {
    Napier.e(message = logMessage(), tag = tag)
}

inline fun error(tag: String, error: Throwable, logMessage: () -> String) {
    Napier.e(throwable = error, message = logMessage.invoke(), tag = tag)
}

inline fun info(tag: String, logMessage: () -> String) {
    Napier.i(message = logMessage.invoke(), tag = tag)
}

inline fun verbose(tag: String, logMessage: () -> String) {
    Napier.v(message = logMessage.invoke(), tag = tag)
}

inline fun warn(tag: String, logMessage: () -> String) {
    Napier.w(message = logMessage.invoke(), tag = tag)
}

inline fun debug(tag: String, logMessage: () -> String) {
    Napier.d(message = logMessage.invoke(), tag = tag)
}

inline fun wtf(tag: String, logMessage: () -> String) {
    Napier.wtf(message = logMessage.invoke(), tag = tag)
}
