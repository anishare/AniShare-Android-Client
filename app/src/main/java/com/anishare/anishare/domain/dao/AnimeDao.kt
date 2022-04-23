package com.anishare.anishare.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.AnimeWithAnimeMAL
import java.util.*

@Dao
interface AnimeDao {

    @Transaction
    @Query("SELECT * FROM Anime")
    fun getAll(): LiveData<List<AnimeWithAnimeMAL>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(anime: Anime)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animeList: List<Anime>)

    @Delete
    suspend fun deleteOne(anime: Anime)

    @Query("SELECT * FROM Anime WHERE id = :id")
    suspend fun getOneById(id: UUID): Anime?
}