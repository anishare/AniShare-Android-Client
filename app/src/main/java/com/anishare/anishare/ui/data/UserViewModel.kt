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
                val response = userService.getTo("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsifQ.eyJleHAiOjE2NDgzMDg2MTIsIm5iZiI6MTY0ODMwNTAxMiwidmVyIjoiMS4wIiwiaXNzIjoiaHR0cHM6Ly9hbmlzaGFyZS5iMmNsb2dpbi5jb20vNmFhODIxZGItNzllMi00ZGNhLWFiMGQtZmRlYTQyMTc1NjZkL3YyLjAvIiwic3ViIjoiNTY2YmYyYWEtMjJkNy00MDgyLWE3NjYtOGY5OGM0YjA4YzhiIiwiYXVkIjoiMWY2YWRjODgtM2RkMi00MzFkLWI2NGQtNDYwNTJiYmMzN2QzIiwiaWF0IjoxNjQ4MzA1MDEyLCJhdXRoX3RpbWUiOjE2NDgyNzYzMzIsIm9pZCI6IjU2NmJmMmFhLTIyZDctNDA4Mi1hNzY2LThmOThjNGIwOGM4YiIsIm5hbWUiOiJ0ZXN0IiwiZW1haWxzIjpbInRlc3R1c2VyQHRlc3QuY29tIl0sInRmcCI6IkIyQ18xX3NpZ25pbl9zaWdudXAifQ.Dw6V5LeBE7OIHmhFdZl1rDaTigqP1s6AowmeeHRluQ0z5zQSBF-nOVPDep41bGYIVyAc0Ot4EUDHMlo_SVwzHBq0zXFVtRw0i7VTN239ZofKt4ZcJwXbZIOx2tPVeIaFce8wNklZztU1XJL6USLFuBrhdsqBasALQkZ_-HJ8w-cRB7VauLGHbX7Z-rgbvc71aCvnkkCPhFeoW0fWnnj2X_q5Pt2VeyuGQ1sgfbVF_0q2Hp_rbKVSQ0weuFn6wjKf-X142mAaKxD4C2EJZSoWARet052kEJLdqMvP5VG92mi1YfLNsN0K2sxZWLgrICFVRzkqRmA0A2qKSSFpsCXtYA")
                Log.i("UserViewModel", response.size.toString())
                userResponse = response
            } catch (e: Exception) {
                Log.e("UserViewModel", e.message.toString())
            }
        }
    }
}