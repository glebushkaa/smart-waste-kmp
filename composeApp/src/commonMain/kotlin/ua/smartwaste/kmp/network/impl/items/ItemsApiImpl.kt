package ua.smartwaste.kmp.network.impl.items

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import ua.smartwaste.kmp.network.api.items.ItemsApi
import ua.smartwaste.kmp.network.impl.FileUploader
import ua.smartwaste.kmp.network.api.items.model.NetworkRubbish
import ua.smartwaste.kmp.network.impl.items.dto.NetworkRubbishListDto
import ua.smartwaste.kmp.network.impl.mapper.toNetworkRubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class ItemsApiImpl(
    private val itemsHttpClient: HttpClient,
    private val fileUploader: FileUploader
) : ItemsApi {
    override suspend fun getAvailableRubbishes(): List<NetworkRubbish> {
        return itemsHttpClient.get("items")
            .call
            .body<NetworkRubbishListDto>()
            .items
            .map { it.toNetworkRubbish() }
    }

    override suspend fun scanRubbish(path: String): NetworkRubbish? {
        val formData = fileUploader.convertFileToPartData(path)
        val request = itemsHttpClient.submitFormWithBinaryData(
            url = "scan",
            formData = formData
        )
        return request.call
            .body<NetworkRubbishListDto>()
            .items
            .firstOrNull()
            ?.toNetworkRubbish()
    }
}
