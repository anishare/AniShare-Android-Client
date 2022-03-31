package com.anishare.anishare.model

import java.util.*

data class UserData(
    val id: UUID,
    val fromUser: String,
    val toUser: String,
    val dateCreated: String,
    val item: Anime,
    val isAnime: Boolean,
    val isFinished: Boolean
) {
    companion object {
        fun mock() = UserData(
            id = UUID.randomUUID(),
            fromUser = "test",
            toUser = "test2",
            dateCreated = "2022-03-22",
            item = Anime.mock(),
            isAnime = true,
            isFinished = false
        )
    }
}
