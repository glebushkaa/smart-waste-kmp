package ua.gleb.smartwaste.network.api.user

import ua.gleb.smartwaste.network.api.user.model.NetworkQuest
import ua.gleb.smartwaste.network.api.user.model.NetworkUser

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface UserApi {

    suspend fun getUser(): NetworkUser

    suspend fun getQuests(): List<NetworkQuest>
}
