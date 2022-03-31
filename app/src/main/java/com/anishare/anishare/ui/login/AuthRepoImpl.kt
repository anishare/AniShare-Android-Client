package com.anishare.anishare.ui.login

import android.util.Log
import com.anishare.anishare.util.LoadingState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult

private const val TAG = "AuthRepo"

class AuthRepoImpl (
    private val auth: FirebaseAuth
) : AuthRepo {
    override fun isUserAuthenticated() = auth.currentUser != null

    override suspend fun signIn(
        email: String,
        password: String,
        setLoadingState: (loadingState: LoadingState) -> Unit
    ) {
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

    override suspend fun signUp(
        email: String,
        password: String,
        setLoadingState: (loadingState: LoadingState) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): Task<GetTokenResult>? {
        return auth.currentUser?.getIdToken(false)
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}