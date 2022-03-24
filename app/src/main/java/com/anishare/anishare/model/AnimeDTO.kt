package com.anishare.anishare.model

data class AnimeDTO(
    val id: String,
    val name: String,
    val malID: String
) {
    companion object {
        fun mock() = AnimeDTO(
            "123",
            "Attack on titan",
            "2"
        )
    }
}
