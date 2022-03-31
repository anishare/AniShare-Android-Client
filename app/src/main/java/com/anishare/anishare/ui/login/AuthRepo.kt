package com.anishare.anishare.ui.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.anishare.anishare.util.LoadingState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "AuthRepo"

@Singleton
class AuthRepo @Inject constructor(
    private val auth: FirebaseAuth
) {
    fun isUserAuthenticated() = auth.currentUser != null

    fun signIn(email: String, password: String, setLoadingState: (loadingState: LoadingState) -> Unit) {
        setLoadingState(LoadingState.LOADING)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "User logged in")
                    setLoadingState(LoadingState.LOADED)
                } else {
                    Log.e(TAG, it.exception?.message.toString())
                    setLoadingState(LoadingState.error(it.exception?.message.toString()))
                }
            }
    }

    fun getToken(): Task<GetTokenResult>? {
        return auth.currentUser?.getIdToken(false)
    }

    fun signOut() {
        auth.signOut()
    }
}