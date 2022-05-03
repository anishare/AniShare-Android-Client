package com.anishare.anishare.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "UserData")
data class UserData(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val fromUser: String,
    val toUser: String,
    val dateCreated: String = Calendar.getInstance().time.toString(),
    val item: UUID,
    val isAnime: Boolean = true,
    val isFinished: Boolean = false
) {
    companion object {
        fun mock() = UserData(
            id = UUID.randomUUID(),
            fromUser = "test",
            toUser = "test2",
            dateCreated = "2022-03-22",
            item = Anime.mock().id,
            isAnime = true,
            isFinished = false
        )
    }
}
