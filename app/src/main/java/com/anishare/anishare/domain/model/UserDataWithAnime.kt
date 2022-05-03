package com.anishare.anishare.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserDataWithAnime(
    @Embedded val userData: UserData,
    @Relation(
        entity = Anime::class,
        parentColumn = "item",
        entityColumn = "id"
    )
    val animeWithAnimeMAL: AnimeWithAnimeMAL
)
