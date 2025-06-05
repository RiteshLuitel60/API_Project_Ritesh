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
    val property1: String,
    val property2: String,
    val description: String
) 