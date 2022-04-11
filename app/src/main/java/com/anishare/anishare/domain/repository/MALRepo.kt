package com.anishare.anishare.domain.repository

import android.util.Log
import com.anishare.anishare.domain.dao.AnimeMALDao
import com.anishare.anishare.domain.model.AnimeMAL
import com.anishare.anishare.domain.model.AnimeMALNode
import com.anishare.anishare.domain.model.MALPaging
import com.anishare.anishare.domain.model.MALResponse
import com.anishare.anishare.network.MALService

private const val TAG = "MALRepo"

//TODO - Cleanup and DB
class MALRepo(
//    private val animeMALDao: AnimeMALDao,
    private val malService: MALService
) {
    suspend fun search(name: String, offset: Int = 0): MALResponse<List<AnimeMALNode>> {
        try {
            return malService.searchAnime(name = name, offset = offset)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
        return MALResponse(listOf(), paging = MALPaging())
    }

    suspend fun getAnime(id: Int): AnimeMAL? {
        try {
            val res = malService.getAnime(id)
//            animeMALDao.insertOne(res)
            return res
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return null
    }
}