package com.anishare.anishare.domain.repository

import androidx.lifecycle.LiveData
import com.anishare.anishare.domain.model.UserData
import java.util.UUID

interface UserDataRepo {

    suspend fun getAll(token: String?): LiveData<List<UserData>>

    suspend fun insertOne(userData: UserData)

    suspend fun deleteOne(userData: UserData)

    suspend fun getOneById(id: UUID): UserData?
}