package com.anishare.anishare.ui.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.AnimeWithAnimeMAL
import com.anishare.anishare.domain.repository.AnimeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val TAG = "AnimeViewModel"

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepo: AnimeRepo
) : ViewModel() {

    private val _anime: MutableLiveData<List<AnimeWithAnimeMAL>> by lazy {
        MutableLiveData<List<AnimeWithAnimeMAL>>().also {
            getData()
        }
    }
    val anime: LiveData<List<AnimeWithAnimeMAL>>
        get() = _anime

    private val _data = MutableLiveData<Anime?>()
    val data: LiveData<Anime?>
        get() = _data

    private fun getData() {
        viewModelScope.launch {
            try {
                animeRepo.getAll().observeForever { _anime.value = it }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
    }

    fun addData(anime: Anime) {
        viewModelScope.launch {
            try {
                animeRepo.insertOne(anime)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
    }

    fun getOne(id: UUID) {
        viewModelScope.launch {
            val res = animeRepo.getOneById(id)
            _data.postValue(res)
        }
    }
}