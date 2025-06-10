package com.example.api_project_ritesh.data.api

import com.example.api_project_ritesh.data.model.DashboardResponseRaw
import com.example.api_project_ritesh.data.model.LoginRequest
import com.example.api_project_ritesh.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("sydney/auth")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): Response<DashboardResponseRaw>
} 