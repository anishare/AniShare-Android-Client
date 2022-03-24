package com.anishare.anishare.model

import java.time.LocalDateTime

data class UserDTO(
    val id: String,
    val fromUser: String,
    val toUser: String,
    val dateCreated: String,
    val item: AnimeDTO,
    val isAnime: Boolean,
    val isFinished: Boolean
) {
    companion object {
        fun mock() = UserDTO(
            id = "1",
            fromUser = "test",
            toUser = "test2",
            dateCreated = "2022-03-22",
            item = AnimeDTO.mock(),
            isAnime = true,
            isFinished = false
        )
    }
}
