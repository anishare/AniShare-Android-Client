package com.anishare.anishare.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//TODO - Remove ID and set malID as Primary Key, use name from MAL instead of user provided name
@Entity(tableName = "Anime")
data class Anime(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val malID: Int
) {
    companion object {
        fun mock() = Anime(
            UUID.randomUUID(),
            "Attack on titan",
            1
        )
    }
}
