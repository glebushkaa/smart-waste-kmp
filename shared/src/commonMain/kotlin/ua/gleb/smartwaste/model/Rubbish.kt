package ua.gleb.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

data class Rubbish(
    val id: Long,
    val title: String,
    val emoji: String,
    val count: Int = 0
) {
    fun incrementCount() = copy(count = count + 1)
    fun decrementCount() = copy(count = count - 1)
}
