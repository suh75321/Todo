package com.teamsparta.todo.domain.dto

data class MemberCreateDto(
    val username: String,
    val password: String,
    val email: String
)