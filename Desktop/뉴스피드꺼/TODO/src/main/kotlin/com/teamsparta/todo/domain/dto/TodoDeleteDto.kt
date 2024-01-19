package com.teamsparta.todo.domain.dto

data class TodoDeleteDto(
    val id: Long,
    val authorName: String,
    val password: String
)