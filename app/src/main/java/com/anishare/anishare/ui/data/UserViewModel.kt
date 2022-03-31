package com.anishare.anishare.ui.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anishare.anishare.model.UserData
import com.anishare.anishare.network.UserService
import com.anishare.anishare.ui.auth.AuthRepo
import com.anishare.anishare.ui.util.UserDataEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UserViewModel"

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userService: UserService,
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _userData: MutableLiveData<List<UserData>> by lazy {
        MutableLiveData<List<UserData>>().also {
            getByTo(UserDataEvent.GetListData)
        }
    }
    val userData: LiveData<List<UserData>>
        get() = _userData

    private fun getByTo(event: UserDataEvent) {
        viewModelScope.launch {
            authRepo.getToken()?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "Grabbed token successfully")
                    when (event) {
                        is UserDataEvent.GetListData -> getData("Bearer ${it.result.token}")
                        is UserDataEvent.GetItem -> Log.d(TAG, "Get Item ${event.id}")
                    }
                } else {
                    Log.e(TAG, it.exception?.message.toString())
                }
            }
        }
    }

    private fun getData(token: String?) {
        viewModelScope.launch {
            try {
                val response = userService.getTo(token!!)
                Log.d(TAG, response.size.toString())
                _userData.value = response
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}