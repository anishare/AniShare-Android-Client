package com.anishare.anishare.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.anishare.anishare.domain.dao.UserDataDao
import com.anishare.anishare.domain.model.UserData
import com.anishare.anishare.network.UserService
import java.util.*

private const val TAG = "UserDataRepo"

class UserDataRepoImpl(
    private val dao: UserDataDao,
    private val userService: UserService
): UserDataRepo {
    override suspend fun getAll(token: String?): LiveData<List<UserData>> {
        try {
            val data = userService.getTo(token!!)
            dao.insertAll(data)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return dao.getAll()
    }

    override suspend fun insertOne(userData: UserData) {
        try {
            //TODO - Insert Call to Backend
            dao.insertOne(userData = userData)
            Log.d(TAG, "Added")
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    override suspend fun deleteOne(userData: UserData) {
        try {
            //TODO - Delete Call to Backend
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    override suspend fun getOneById(id: UUID): UserData? {
        return dao.getOneById(id)
    }

}