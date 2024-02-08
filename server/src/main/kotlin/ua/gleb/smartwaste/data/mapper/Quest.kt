package ua.gleb.smartwaste.data.mapper

import org.jetbrains.exposed.sql.ResultRow
import ua.gleb.smartwaste.database.quest.entity.QuestEntity
import ua.gleb.smartwaste.database.quest.entity.UserQuestEntity
import ua.gleb.smartwaste.database.quest.table.QuestTable
import ua.gleb.smartwaste.database.quest.table.UserQuestTable
import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.network.user.dto.NetworkQuestDto

fun toQuestEntity(row: ResultRow) = QuestEntity(
    id = row[QuestTable.id],
    name = row[QuestTable.name],
    requiredProgress = row[QuestTable.requiredProgress],
)

fun toUserQuestEntity(row: ResultRow) = UserQuestEntity(
    id = row[QuestTable.id],
    name = row[QuestTable.name],
    requiredProgress = row[QuestTable.requiredProgress],
    progress = row[UserQuestTable.progress]
)

fun QuestEntity.toQuest(): Quest {
    return Quest(
        id = this.id ?: 0,
        title = this.name ?: "",
        requiredProgress = this.requiredProgress ?: 0,
        currentProgress = 0
    )
}

fun Quest.toNetworkQuestDto(): NetworkQuestDto {
    return NetworkQuestDto(
        id = this.id,
        name = this.title,
        total = this.requiredProgress,
        completed = this.currentProgress
    )
}