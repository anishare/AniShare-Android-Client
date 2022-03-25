package com.anishare.anishare.network.dto

import com.anishare.anishare.model.Anime
import java.util.*

data class UserResponseDTO(
    val id: UUID,
    val fromUser: String,
    val toUser: String,
    val dateCreated: String,
    val item: Anime,
    val isAnime: Boolean,
    val isFinished: Boolean
)
