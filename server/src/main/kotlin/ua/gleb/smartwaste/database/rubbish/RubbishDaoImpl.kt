package ua.gleb.smartwaste.database.rubbish

import ua.gleb.smartwaste.database.SmartWasteDatabase.dbQuery
import ua.gleb.smartwaste.database.rubbish.entity.RubbishEntity

class RubbishDaoImpl : RubbishDao {

    override suspend fun addNewRubbish(title: String, emoji: String) = dbQuery {
        RubbishEntity.new {
            this.title = title
            this.emoji = emoji
        }
        return@dbQuery
    }

    override suspend fun getAllRubbishes(): List<RubbishEntity> = dbQuery {
        return@dbQuery RubbishEntity.all().toList()
    }

    override suspend fun deleteRubbish(id: Long) = dbQuery {
        RubbishEntity.findById(id)?.delete()
        return@dbQuery
    }
}