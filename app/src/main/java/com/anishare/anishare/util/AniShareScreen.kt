package com.anishare.anishare.util

enum class AniShareScreen {
    Login,
    Dashboard;

    companion object {
        fun fromRoute(route: String?): AniShareScreen =
            when (route?.substringBefore("/")) {
                Login.name -> Login
                Dashboard.name -> Dashboard
                null -> Dashboard
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}