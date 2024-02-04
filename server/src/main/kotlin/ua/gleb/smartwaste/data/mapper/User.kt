package ua.gleb.smartwaste.data.mapper

import org.jetbrains.exposed.sql.ResultRow
import ua.gleb.smartwaste.database.user.entity.UserEntity
import ua.gleb.smartwaste.database.user.table.UserTable
import ua.gleb.smartwaste.model.User
import ua.gleb.smartwaste.network.user.dto.NetworkUserDto

fun toUserEntity(row: ResultRow) = UserEntity(
    id = row[UserTable.id].toString(),
    username = row[UserTable.name],
    email = row[UserTable.email],
    level = row[UserTable.level],
    currentProgress = row[UserTable.currentProgress],
    requiredProgress = row[UserTable.requiredProgress],
    createdAt = row[UserTable.createdAt],
    buckets = row[UserTable.buckets]
)

fun UserEntity.toUser(days: Int) = User(
    id = this.id ?: "",
    username = this.username ?: "",
    email = this.email ?: "",
    level = this.level ?: 0,
    currentProgress = this.currentProgress ?: 0,
    requiredProgress = this.requiredProgress ?: 500,
    days = days,
    buckets = this.buckets ?: 0
)

fun User.toNetworkUser(): NetworkUserDto {
    return NetworkUserDto(
        id = this.id,
        email = this.email,
        username = this.username,
        level = this.level,
        days = this.days,
        buckets = this.buckets,
    )
}