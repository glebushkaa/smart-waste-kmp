package ua.gleb.smartwaste.network

enum class Routes(val route: String) {
    AUTH("auth"), USER("self"), QUEST("quest"), RUBBISH("rubbish")
}

enum class AuthRoutes(val route: String) {
    SIGN_IN("signin"), SIGN_UP("signup")
}

enum class UserRoutes(val route: String) {
    ME("me"), QUESTS("quests"), NEW_QUEST("questprogress") // TODO Rename or remove NEW_QUEST
}

enum class QuestRoutes(val route: String) {
    ALL("all")
}

enum class RubbishRoutes(val route: String) {
    ALL("all"), NEW("new")
}