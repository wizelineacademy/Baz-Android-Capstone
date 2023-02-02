package com.example.baz_android_capstone.presentation.navigation

const val DATA_ARGUMENT_KEY = "book_name"

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Principal : Screen(route = "principal_screen")
    object Description : Screen(route = "description_screen/{$DATA_ARGUMENT_KEY}") {
        fun passArgs(
            book: String
        ): String {
            return this.route.replace(
                oldValue = "{$DATA_ARGUMENT_KEY}",
                newValue = book
            )
        }
    }
}
