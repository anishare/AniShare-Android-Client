package com.anishare.anishare.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anishare.anishare.domain.model.UserData
import java.util.UUID

@Dao
interface UserDataDao {

    @Query("SELECT * FROM UserData")
    fun getAll(): LiveData<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(userData: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataList: List<UserData>)

    @Delete
    suspend fun deleteOne(userData: UserData)

    @Query("SELECT * FROM UserData WHERE id = :id")
    suspend fun getOneById(id: UUID): UserData?
}