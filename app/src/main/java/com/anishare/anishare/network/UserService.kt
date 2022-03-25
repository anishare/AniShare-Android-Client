package com.anishare.anishare.network

import com.anishare.anishare.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("/user/getTo")
    suspend fun getTo(
        @Header("Authorization") token: String
    ): List<UserResponse>
}