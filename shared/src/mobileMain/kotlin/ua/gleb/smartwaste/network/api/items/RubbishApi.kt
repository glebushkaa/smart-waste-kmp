package ua.gleb.smartwaste.network.api.items

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

interface RubbishApi {

    suspend fun getAvailableRubbishes(): List<ua.gleb.smartwaste.network.api.items.model.NetworkRubbish>

    suspend fun scanRubbish(path: String): ua.gleb.smartwaste.network.api.items.model.NetworkRubbish?
}
