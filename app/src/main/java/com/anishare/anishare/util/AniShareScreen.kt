package com.anishare.anishare.util

enum class AniShareScreen {
    Login,
    SignUp,
    Dashboard,
    AddItem,
    Recommend;

    companion object {
        fun fromRoute(route: String?): AniShareScreen =
            when (route?.substringBefore("/")) {
                Login.name -> Login
                Dashboard.name -> Dashboard
                AddItem.name -> AddItem
                Recommend.name -> Recommend
                null -> Dashboard
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}