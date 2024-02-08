package ua.gleb.smartwaste.database.quest

import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.Table.Dual.default
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.selectAll
import ua.gleb.smartwaste.data.mapper.toQuestEntity
import ua.gleb.smartwaste.data.mapper.toUserQuestEntity
import ua.gleb.smartwaste.database.SmartWasteDatabase.dbQuery
import ua.gleb.smartwaste.database.quest.entity.QuestEntity
import ua.gleb.smartwaste.database.quest.entity.UserQuestEntity
import ua.gleb.smartwaste.database.quest.table.QuestTable
import ua.gleb.smartwaste.database.quest.table.UserQuestTable
import java.util.*

class QuestDaoImpl : QuestDao {

    override suspend fun getAllQuests(): List<QuestEntity> = dbQuery {
        return@dbQuery QuestTable.selectAll().map(::toQuestEntity)
    }

    override suspend fun getUserQuests(id: UUID): List<UserQuestEntity> = dbQuery {
        return@dbQuery QuestTable.join(
            otherTable = UserQuestTable,
            joinType = JoinType.LEFT,
            onColumn = QuestTable.id,
            otherColumn = UserQuestTable.questId,
            additionalConstraint = {
                (UserQuestTable.userId eq id)
            }
        ).select(
            QuestTable.id,
            QuestTable.name,
            QuestTable.requiredProgress,
            UserQuestTable.progress.default(0)
        ).map(::toUserQuestEntity)
    }

    override suspend fun getQuests(): List<QuestEntity> = dbQuery {
        QuestTable.selectAll().map(::toQuestEntity)
    }

    override suspend fun insertQuest(name: String, requiredProgress: Int) = dbQuery {
        QuestTable.insertIgnore { table ->
            table[QuestTable.name] = name
            table[QuestTable.requiredProgress] = requiredProgress
        }
        return@dbQuery
    }

    override suspend fun insertUserQuest(questId: Long, userId: UUID, progress: Int) = dbQuery {
        UserQuestTable.insert { table ->
            table[UserQuestTable.userId] = userId
            table[UserQuestTable.questId] = questId
            table[UserQuestTable.progress] = progress
        }
        return@dbQuery
    }
}