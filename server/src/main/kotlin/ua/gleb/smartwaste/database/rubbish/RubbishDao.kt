package ua.gleb.smartwaste.database.rubbish

import ua.gleb.smartwaste.database.rubbish.entity.RubbishEntity

interface RubbishDao {

    suspend fun addNewRubbish(title: String, emoji: String)

    suspend fun getAllRubbishes(): List<RubbishEntity>

    suspend fun deleteRubbish(id: Long)
}