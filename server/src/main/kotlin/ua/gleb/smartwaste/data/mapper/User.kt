package ua.gleb.smartwaste.data.mapper

import org.jetbrains.exposed.sql.ResultRow
import ua.gleb.smartwaste.database.user.entity.UserEntity
import ua.gleb.smartwaste.database.user.table.UserTable
import ua.gleb.smartwaste.model.User
import ua.gleb.smartwaste.network.user.dto.NetworkUserDto

fun toUserEntity(row: ResultRow) = UserEntity(
    id = row[UserTable.id].toString(),
    username = row[UserTable.name],
    password = row[UserTable.password],
    email = row[UserTable.email],
    progress = row[UserTable.progress],
    requiredProgress = row[UserTable.requiredProgress],
    createdAt = row[UserTable.createdAt],
    buckets = row[UserTable.buckets]
)

fun UserEntity.toUser(days: Int): User {
    val progress = (this.progress ?: 0)
    val requiredProgress = (this.requiredProgress ?: 500)
    val level = progress / requiredProgress
    val completedProgressToNextLevel = progress % requiredProgress
    return User(
        id = this.id ?: "",
        username = this.username ?: "",
        email = this.email ?: "",
        level = level,
        currentProgress = completedProgressToNextLevel,
        requiredProgress = requiredProgress,
        days = days,
        buckets = this.buckets ?: 0
    )
}

fun User.toNetworkUserDto(): NetworkUserDto {
    return NetworkUserDto(
        id = this.id,
        email = this.email,
        username = this.username,
        level = this.level,
        progress = this.currentProgress,
        requiredProgress = this.requiredProgress,
        days = this.days,
        buckets = this.buckets,
    )
}