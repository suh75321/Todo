package com.teamsparta.todo.domain.dto

data class CommentCreateDto(
    val content: String,
    val authorName: String,
    val todoId: Long,
    val password: String
)