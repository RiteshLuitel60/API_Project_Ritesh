package com.example.api_project_ritesh.data.model

import java.io.Serializable

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

// Raw response from API
// entities is a list of maps
// This is what Retrofit/Gson will parse
// We'll convert this to DashboardResponse in the repository
data class DashboardResponseRaw(
    val entities: List<Map<String, Any?>>, // List of maps
    val entityTotal: Int
)

// A single record from the API
typealias EntityMap = Map<String, Any?>

// Wrapper class to make Entity serializable
data class Entity(
    private val map: EntityMap
) : Serializable, Map<String, Any?> by map {
    companion object {
        private const val serialVersionUID = 1L
    }
}

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
) 