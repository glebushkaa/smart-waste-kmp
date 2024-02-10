package ua.gleb.smartwaste.domain.repository

import ua.gleb.smartwaste.network.rubbish.response.NetworkRubbishResponse

interface RubbishRepository {

    suspend fun addNewRubbish(title: String, emoji: String)

    suspend fun getAllRubbishes(): List<NetworkRubbishResponse>

    suspend fun deleteRubbish(id: Long)
}