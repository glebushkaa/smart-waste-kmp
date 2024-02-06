package ua.gleb.smartwaste.network

enum class Routes(val route: String) {
    AUTH("auth"), USER("self")
}

enum class AuthRoutes(val route: String) {
    SIGN_IN("signin"), SIGN_UP("signup")
}

enum class UserRoutes(val route: String) {
    ME("me"), QUESTS("quests")
}