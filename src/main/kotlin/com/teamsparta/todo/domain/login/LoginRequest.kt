package com.teamsparta.todo.domain.login

data class LoginRequest(
    val email: String,
    val password: String,
    val role: String
)