package ua.gleb.smartwaste.data.repository

import ua.gleb.smartwaste.data.mapper.toNetworkRubbish
import ua.gleb.smartwaste.database.rubbish.RubbishDao
import ua.gleb.smartwaste.domain.repository.RubbishRepository
import ua.gleb.smartwaste.network.rubbish.response.NetworkRubbishResponse

class RubbishRepositoryImpl(
    private val rubbishDao: RubbishDao
) : RubbishRepository {

    override suspend fun addNewRubbish(title: String, emoji: String) {
        rubbishDao.addNewRubbish(title, emoji)
    }

    override suspend fun getAllRubbishes(): List<NetworkRubbishResponse> {
        return rubbishDao.getAllRubbishes().map {
            it.toNetworkRubbish()
        }
    }

    override suspend fun deleteRubbish(id: Long) {
        rubbishDao.deleteRubbish(id)
    }
}