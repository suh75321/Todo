package com.teamsparta.todo.domain.service

import com.teamsparta.todo.domain.dto.*
import jakarta.transaction.Transactional

interface TodoService {
    fun create(todoCreateDto: TodoCreateDto): TodoDto
    fun getAll(): List<TodoDto>
    fun getById(id: Long): TodoDto
    fun update(id: Long, todoUpdateDto: TodoUpdateDto): TodoDto
    fun delete(id: Long)
    @Transactional
    fun updateComment(id: Long, commentUpdateDto: CommentUpdateDto): CommentDto
    @Transactional
    fun createComment(commentCreateDto: CommentCreateDto): CommentDto
}