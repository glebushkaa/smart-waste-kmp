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
import ua.gleb.smartwaste.database.quest.table.QuestsTable
import ua.gleb.smartwaste.database.quest.table.UserQuestsTable
import java.util.*

class QuestDaoImpl : QuestDao {

    override suspend fun getAllQuests(): List<QuestEntity> = dbQuery {
        return@dbQuery QuestsTable.selectAll().map(::toQuestEntity)
    }

    override suspend fun getUserQuests(id: UUID): List<UserQuestEntity> = dbQuery {
        return@dbQuery QuestsTable.join(
            otherTable = UserQuestsTable,
            joinType = JoinType.LEFT,
            onColumn = QuestsTable.id,
            otherColumn = UserQuestsTable.questId,
            additionalConstraint = {
                (UserQuestsTable.userId eq id)
            }
        ).select(
            QuestsTable.id,
            QuestsTable.name,
            QuestsTable.requiredProgress,
            UserQuestsTable.progress.default(0)
        ).map(::toUserQuestEntity)
    }

    override suspend fun getQuests(): List<QuestEntity> = dbQuery {
        QuestsTable.selectAll().map(::toQuestEntity)
    }

    override suspend fun insertQuest(name: String, requiredProgress: Int) = dbQuery {
        QuestsTable.insertIgnore { table ->
            table[QuestsTable.name] = name
            table[QuestsTable.requiredProgress] = requiredProgress
        }
        return@dbQuery
    }

    override suspend fun insertUserQuest(questId: Long, userId: UUID, progress: Int) = dbQuery {
        UserQuestsTable.insert { table ->
            table[UserQuestsTable.userId] = userId
            table[UserQuestsTable.questId] = questId
            table[UserQuestsTable.progress] = progress
        }
        return@dbQuery
    }
}