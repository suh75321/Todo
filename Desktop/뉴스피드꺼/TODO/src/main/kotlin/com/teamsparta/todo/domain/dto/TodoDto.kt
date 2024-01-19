package com.teamsparta.todo.domain.dto

data class TodoDto(
    val id: Long?,
    val title: String,
    val content: String,
    val authorName: String
) {
    companion object {
        fun from(todo: com.teamsparta.todo.domain.model.Todo): TodoDto {
            return TodoDto(
                id = todo.id,
                title = todo.title,
                content = todo.content,
                authorName = todo.authorName
            )
        }
    }
}