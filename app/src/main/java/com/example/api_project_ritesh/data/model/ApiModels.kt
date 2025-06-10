package com.example.api_project_ritesh.data.model

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

data class Entity(
    val name: String,
    val architect: String,
    val location: String,
    val yearCompleted: Int,
    val style: String,
    val height: Int,
    val description: String
) 