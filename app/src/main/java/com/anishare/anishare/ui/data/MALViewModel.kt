package com.anishare.anishare.ui.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anishare.anishare.domain.model.AnimeMAL
import com.anishare.anishare.domain.model.AnimeMALNode
import com.anishare.anishare.domain.repository.MALRepo
import com.anishare.anishare.network.MALSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MALViewModel"

@HiltViewModel
class MALViewModel @Inject constructor(
    private val malRepo: MALRepo
) : ViewModel() {

    fun searchResults(query: String): Flow<PagingData<AnimeMALNode>> {
        return Pager(PagingConfig(10)) { MALSource(malRepo = malRepo, query = query) }.flow
    }

    fun addData(animeMAL: AnimeMAL) {
        viewModelScope.launch {
            try {
                malRepo.insertOneToDB(animeMAL)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
    }
}