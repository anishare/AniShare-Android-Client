package com.anishare.anishare.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class AnimeWithAnimeMAL(
    @Embedded val anime: Anime,
    @Relation(
        entity = AnimeMAL::class,
        parentColumn = "malID",
        entityColumn = "id"
    )
    val animeMAL: AnimeMAL
)
