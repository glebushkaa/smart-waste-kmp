package ua.gleb.smartwaste.core

const val SERVER_PORT = 8080
const val BASE_URL = "192.168.31.138"
//private const val BASE_URL = "smartwaste-api.azurewebsites.net"

enum class Routes(val route: String) {
    AUTH("auth"), USER("self")
}