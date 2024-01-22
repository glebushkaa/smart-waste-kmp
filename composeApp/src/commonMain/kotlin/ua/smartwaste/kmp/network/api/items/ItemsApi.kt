package ua.smartwaste.kmp.network.api.items

import ua.smartwaste.kmp.network.api.items.model.NetworkRubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

interface ItemsApi {

    suspend fun getAvailableRubbishes(): List<NetworkRubbish>

    suspend fun scanRubbish(path: String): NetworkRubbish?
}
