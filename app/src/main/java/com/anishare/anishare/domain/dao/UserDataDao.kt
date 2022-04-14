package com.anishare.anishare.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anishare.anishare.domain.model.UserData
import com.anishare.anishare.domain.model.UserDataWithAnime
import java.util.UUID

@Dao
interface UserDataDao {

    @Transaction
    @Query("SELECT * FROM UserData")
    fun getAll(): LiveData<List<UserDataWithAnime>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(userData: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataList: List<UserData>)

    @Delete
    suspend fun deleteOne(userData: UserData)

    @Query("SELECT * FROM UserData WHERE id = :id")
    suspend fun getOneById(id: UUID): UserData?
}