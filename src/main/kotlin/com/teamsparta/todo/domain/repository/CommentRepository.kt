package com.teamsparta.todo.domain.repository

import com.teamsparta.todo.domain.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByAuthorName(authorName: String): List<Comment>
}