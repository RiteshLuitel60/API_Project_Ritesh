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
// Itll convert this to DashboardResponse in the repository
data class DashboardResponseRaw(
    val entities: List<Map<String, Any?>>, // List of maps
    val entityTotal: Int
)

// A single record from the API
typealias EntityMap = Map<String, Any?>

// Wrapper class to make Entity serializable
/* Serializable: This indicates that instances of
 Entity can be serialized (converted into a byte stream,
 often for storage or transmission).*/
data class Entity(
    private val map: EntityMap
) : Serializable, Map<String, Any?> by map {
    companion object {
        private const val serialVersionUID = 1L //This is standard practice when implementing
    }
}

/*This data class represents the processed or final version of the
dashboard data, ready to be used by the application's UI or business logic. */
data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
) 