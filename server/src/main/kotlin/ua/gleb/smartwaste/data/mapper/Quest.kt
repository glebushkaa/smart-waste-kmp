package ua.gleb.smartwaste.data.mapper

import org.jetbrains.exposed.sql.ResultRow
import ua.gleb.smartwaste.database.quest.entity.QuestEntity
import ua.gleb.smartwaste.database.quest.entity.UserQuestEntity
import ua.gleb.smartwaste.database.quest.table.QuestsTable
import ua.gleb.smartwaste.database.quest.table.UserQuestsTable
import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.network.quest.response.NetworkQuestResponse

fun toQuestEntity(row: ResultRow) = QuestEntity(
    id = row[QuestsTable.id],
    name = row[QuestsTable.name],
    requiredProgress = row[QuestsTable.requiredProgress],
)

fun toUserQuestEntity(row: ResultRow) = UserQuestEntity(
    id = row[QuestsTable.id],
    name = row[QuestsTable.name],
    requiredProgress = row[QuestsTable.requiredProgress],
    progress = row[UserQuestsTable.progress]
)

fun QuestEntity.toQuest(): Quest {
    return Quest(
        id = this.id ?: 0,
        title = this.name ?: "",
        requiredProgress = this.requiredProgress ?: 0,
        currentProgress = 0
    )
}

fun Quest.toNetworkQuestDto(): NetworkQuestResponse {
    return NetworkQuestResponse(
        id = this.id,
        name = this.title,
        total = this.requiredProgress,
        completed = this.currentProgress
    )
}