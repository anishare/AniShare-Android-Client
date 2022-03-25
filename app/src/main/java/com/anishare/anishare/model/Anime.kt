package com.anishare.anishare.model

import java.util.*

data class Anime(
    val id: UUID,
    val name: String,
    val malID: String
) {
    companion object {
        fun mock() = Anime(
            UUID.randomUUID(),
            "Attack on titan",
            "2"
        )
    }
}
