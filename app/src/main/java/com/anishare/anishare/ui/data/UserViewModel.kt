package com.anishare.anishare.ui.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anishare.anishare.domain.model.UserData
import com.anishare.anishare.domain.model.UserDataWithAnime
import com.anishare.anishare.domain.repository.UserDataRepo
import com.anishare.anishare.ui.auth.AuthRepo
import com.anishare.anishare.ui.util.UserDataEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UserViewModel"

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDataRepo: UserDataRepo,
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _userData: MutableLiveData<List<UserDataWithAnime>> by lazy {
        MutableLiveData<List<UserDataWithAnime>>().also {
            launchEvent(UserDataEvent.GetListData)
        }
    }
    val userData: LiveData<List<UserDataWithAnime>>
        get() = _userData

    fun launchEvent(event: UserDataEvent) {
        viewModelScope.launch {
            if (authRepo.isUserAuthenticated()) {
                authRepo.getToken()?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "Grabbed token successfully")
                        callMethod(event = event, token = "Bearer ${it.result.token}")
                    } else {
                        Log.e(TAG, "Get Token failed ${it.exception?.message.toString()}")
                    }
                }
            } else {
                callMethod(event = event)
            }
        }
    }

    private fun callMethod(event: UserDataEvent, token: String? = null) {
        when (event) {
            is UserDataEvent.GetListData -> getData(token)
            is UserDataEvent.GetItem -> Log.d(TAG, "Get Item ${event.id}")
            is UserDataEvent.AddItem -> addItem(event.userData)
        }
    }

    private fun getData(token: String?) {
        viewModelScope.launch {
            try {
                val response = userDataRepo.getAll(token)
                Log.d(TAG, response.value?.size.toString())
                response.observeForever { t -> _userData.value = t }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to get data $e")
            }
        }
    }

    private fun addItem(item: UserData) {
        Log.d(TAG, "Getting Data")
        viewModelScope.launch {
            userDataRepo.insertOne(item)
        }
    }
}