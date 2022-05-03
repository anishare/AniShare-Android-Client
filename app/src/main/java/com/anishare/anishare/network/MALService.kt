package com.anishare.anishare.network

import com.anishare.anishare.domain.model.AnimeMAL
import com.anishare.anishare.domain.model.AnimeMALNode
import com.anishare.anishare.domain.model.MALResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MALService {

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") name: String,
        @Query("offset") offset: Int
    ): MALResponse<List<AnimeMALNode>>

    @GET("anime/{id}")
    suspend fun getAnime(
        @Path("id") id: Int
    ): AnimeMAL
}