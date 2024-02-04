package ua.gleb.smartwaste.database.user

import org.jetbrains.exposed.sql.selectAll
import ua.gleb.smartwaste.data.mapper.toUserEntity
import ua.gleb.smartwaste.database.SmartWasteDatabase.dbQuery
import ua.gleb.smartwaste.database.user.entity.UserEntity
import ua.gleb.smartwaste.database.user.table.UserTable
import java.util.*

class UserDaoImpl : UserDao {

    override suspend fun getAllUsers(): List<UserEntity> = dbQuery {
        UserTable.selectAll().map(::toUserEntity)
    }

    override suspend fun getUser(id: UUID): UserEntity? = dbQuery {
        UserTable.selectAll()
            .where { UserTable.id eq id }
            .map(::toUserEntity)
            .singleOrNull()
    }
}