package com.anishare.anishare.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anishare.anishare.domain.model.AnimeMAL


//TODO - Cleanup
@Dao
interface AnimeMALDao {

//    @Query("SELECT * FROM AnimeMAL")
//    fun getAll(): LiveData<List<AnimeMAL>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(animeMAL: AnimeMAL)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animeMALList: List<AnimeMAL>)

    @Delete
    suspend fun deleteOne(animeMAL: AnimeMAL)

//    @Query("SELECT * FROM AnimeMAL WHERE id = :id")
//    suspend fun getOneById(id: Int): AnimeMAL?
}