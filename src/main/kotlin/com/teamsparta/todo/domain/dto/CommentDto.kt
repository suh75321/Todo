package com.teamsparta.todo.domain.dto

import com.teamsparta.todo.domain.model.Comment

class CommentDto(
    val id: Long,
    val content: String,
    val authorName: String,
    val todoId: Long
) {
    companion object {
        fun from(comment: Comment) = CommentDto(
            id = comment.id ?: throw IllegalArgumentException("Comment id must not be null"),
            content = comment.content,
            authorName = comment.authorName,
            todoId = comment.todo.id ?: throw IllegalArgumentException("Todo id must not be null")
        )
    }
}