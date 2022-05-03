package com.anishare.anishare.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anishare.anishare.domain.dao.AnimeDao
import com.anishare.anishare.domain.dao.AnimeMALDao
import com.anishare.anishare.domain.dao.UserDataDao
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.AnimeMAL
import com.anishare.anishare.domain.model.UserData

// TODO - Add MalDao and MAL class
@Database(
    entities = [Anime::class, UserData::class, AnimeMAL::class],
    version = 1
)
abstract class AniShareDB: RoomDatabase() {

    abstract val userDataDao: UserDataDao

    abstract val animeDao: AnimeDao

    abstract val animeMALDao: AnimeMALDao
}