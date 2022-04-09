package com.anishare.anishare.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Anime")
data class Anime(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
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
