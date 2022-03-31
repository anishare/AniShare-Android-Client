package com.anishare.anishare.ui.login

import com.anishare.anishare.util.LoadingState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GetTokenResult

interface AuthRepo {
    fun isUserAuthenticated(): Boolean
    suspend fun signIn(email: String, password: String, setLoadingState: (loadingState: LoadingState) -> Unit)
    suspend fun signUp(email: String, password: String, setLoadingState: (loadingState: LoadingState) -> Unit)
    suspend fun getToken(): Task<GetTokenResult>?
    suspend fun signOut()
}