package com.example.api_project_ritesh.data.repository

import com.example.api_project_ritesh.data.api.ApiService
import com.example.api_project_ritesh.data.model.DashboardResponse
import com.example.api_project_ritesh.data.model.DashboardResponseRaw
import com.example.api_project_ritesh.data.model.Entity
import com.example.api_project_ritesh.data.model.LoginRequest
import com.example.api_project_ritesh.data.model.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("Login failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDashboard(keypass: String): Result<DashboardResponse> {
        return try {
            val response = apiService.getDashboard(keypass)
            if (response.isSuccessful) {
                response.body()?.let { rawResponse ->
                    val wrappedEntities = rawResponse.entities.map { Entity(it) }
                    Result.success(DashboardResponse(wrappedEntities, rawResponse.entityTotal))
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("Failed to fetch dashboard: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 