package com.example.muztest.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen") //TODO
    object ChatScreen : Screen("chat_screen")
}
