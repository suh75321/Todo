package com.teamsparta.todo.domain.dto

data class TodoCreateDto(
    val title: String,
    val content: String,
    val authorName: String
)