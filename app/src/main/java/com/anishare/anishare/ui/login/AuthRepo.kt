package com.anishare.anishare.ui.login

import android.util.Log
import androidx.compose.runtime.MutableState
import com.anishare.anishare.util.LoadingState
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "AuthRepo"

@Singleton
class AuthRepo @Inject constructor(
    private val auth: FirebaseAuth
) {
    fun isUserAuthenticated() = auth.currentUser != null

    fun signin(email: String, password: String, loadingState: MutableState<LoadingState>) {
        loadingState.value = LoadingState.LOADING
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "User logged in")
                    loadingState.value = LoadingState.LOADED
                } else {
                    Log.e(TAG, it.exception?.message.toString())
                    loadingState.value = LoadingState.error(it.exception?.message.toString())
                }
            }
//        Log.d(TAG, state.status.name)
//        return state
    }

    fun signout() {
        auth.signOut()
    }
}