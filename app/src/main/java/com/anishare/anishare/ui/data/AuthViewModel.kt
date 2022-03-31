package com.anishare.anishare.ui.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anishare.anishare.ui.login.AuthRepo
import com.anishare.anishare.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AuthViewModel"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _loadingState = MutableLiveData(LoadingState.IDLE)
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    fun setLoadingState(loadingState: LoadingState) {
        _loadingState.value = loadingState
    }


    fun isSignedIn(): Boolean {
        return authRepo.isUserAuthenticated()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            setLoadingState(LoadingState.LOADING)
            if (authRepo.isUserAuthenticated()) {
                Log.e(TAG,"User already logged in")
                setLoadingState(LoadingState.error("User already logged in"))
            } else {
                authRepo.signIn(email, password, setLoadingState = { setLoadingState(it) })
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepo.signOut()
        }
    }
}