package com.anishare.anishare.network

import com.anishare.anishare.domain.model.UserData
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("/users/getTo")
    suspend fun getTo(
        @Header("Authorization") token: String
    ): List<UserData>
}