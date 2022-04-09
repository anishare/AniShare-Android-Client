package com.anishare.anishare.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anishare.anishare.domain.dao.UserDataDao
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.UserData

@Database(
    entities = [Anime::class, UserData::class],
    version = 1
)
abstract class AniShareDB: RoomDatabase() {

    abstract val userDataDao: UserDataDao
}