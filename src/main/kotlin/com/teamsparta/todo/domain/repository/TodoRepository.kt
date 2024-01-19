package com.teamsparta.todo.domain.repository

import com.teamsparta.todo.domain.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

    fun findByAuthorName(authorName: String): List<Todo>

    // ... 기타 메소드들 ...
}