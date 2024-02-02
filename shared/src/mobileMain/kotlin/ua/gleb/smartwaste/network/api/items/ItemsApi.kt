package ua.gleb.smartwaste.network.api.items

import ua.gleb.smartwaste.network.api.items.model.NetworkRubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

interface ItemsApi {

    suspend fun getAvailableRubbishes(): List<ua.gleb.smartwaste.network.api.items.model.NetworkRubbish>

    suspend fun scanRubbish(path: String): ua.gleb.smartwaste.network.api.items.model.NetworkRubbish?
}
