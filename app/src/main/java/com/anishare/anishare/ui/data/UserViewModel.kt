package com.anishare.anishare.ui.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anishare.anishare.model.UserResponse
import com.anishare.anishare.network.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userService: UserService
): ViewModel() {

    var userResponse: List<UserResponse> by mutableStateOf(listOf())

    fun getByTo() {
        viewModelScope.launch {
            try {
                userResponse = userService.getTo("abc")
            } catch (e: Exception) {
                Log.e("UserViewModel", e.message.toString())
            }
        }
    }
}