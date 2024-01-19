package com.teamsparta.todo.domain.dto

data class MemberUpdateDto(
    val username: String,
    val password: String,
    val email: String
)