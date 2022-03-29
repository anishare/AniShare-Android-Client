package com.anishare.anishare.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anishare.anishare.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AuthViewModel"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    val loadingState = mutableStateOf(LoadingState.IDLE)

    fun isSignedIn(): Boolean {
        return authRepo.isUserAuthenticated()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            loadingState.value = LoadingState.LOADING
            if (authRepo.isUserAuthenticated()) {
                Log.e(TAG,"User already logged in")
                loadingState.value = LoadingState.error("User already logged in")
            } else {
                authRepo.signIn(email, password, loadingState = loadingState)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepo.signOut()
        }
    }
}