package com.anishare.anishare.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.anishare.anishare.domain.dao.AnimeDao
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.AnimeWithAnimeMAL
import com.anishare.anishare.domain.model.UserDataWithAnime
import java.util.*

private const val TAG = "AnimeRepo"

class AnimeRepo(
    private val animeDao: AnimeDao
) {
    suspend fun getAll(): LiveData<List<AnimeWithAnimeMAL>> {
        return animeDao.getAll()
    }

    suspend fun insertOne(anime: Anime) {
        try {
            return animeDao.insertOne(anime)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    suspend fun getOneById(id: UUID): Anime? {
        try {
            return animeDao.getOneById(id)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
        return null
    }

    suspend fun deleteOne(anime: Anime) {
        try {
            animeDao.deleteOne(anime)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}