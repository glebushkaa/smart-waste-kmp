package ua.gleb.smartwaste.core

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

fun <T, R> List<T>.mapToImmutable(transform: (T) -> R): ImmutableList<R> {
    val persistentList = persistentListOf<R>().builder()
    forEach { item -> persistentList += transform(item) }
    return persistentList.build()
}