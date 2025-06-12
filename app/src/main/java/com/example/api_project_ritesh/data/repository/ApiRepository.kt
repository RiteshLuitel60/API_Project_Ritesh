// Repository class that handles API calls and data transformation - manages network operations and error handling
package com.example.api_project_ritesh.data.repository

import com.example.api_project_ritesh.data.api.ApiService
import com.example.api_project_ritesh.data.model.DashboardResponse
import com.example.api_project_ritesh.data.model.DashboardResponseRaw
import com.example.api_project_ritesh.data.model.Entity
import com.example.api_project_ritesh.data.model.LoginRequest
import com.example.api_project_ritesh.data.model.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

/*this ApiRepository is a singleton that wraps  ApiService to
Perform the network calls for login and dashboard data,
Check HTTP success, map raw API models into app's Entity/DashboardResponse types,
and wrap everything in a Kotlin Result so callers get a clear success-or-failure outcome.*/

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