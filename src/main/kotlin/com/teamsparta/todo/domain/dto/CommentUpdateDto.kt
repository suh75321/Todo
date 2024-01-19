package com.teamsparta.todo.domain.dto

data class CommentUpdateDto(
    val id: Long,
    val content: String,
    val authorName: String,
    val password: String
)