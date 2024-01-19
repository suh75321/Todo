package com.teamsparta.todo.domain.service

import com.teamsparta.todo.domain.dto.CommentCreateDto
import com.teamsparta.todo.domain.dto.CommentDeleteDto
import com.teamsparta.todo.domain.dto.CommentDto
import com.teamsparta.todo.domain.dto.CommentUpdateDto

interface CommentService {
    fun createComment(commentCreateDto: CommentCreateDto): CommentDto
    fun updateComment(commentUpdateDto: CommentUpdateDto): CommentDto
    fun deleteComment(commentDeleteDto: CommentDeleteDto)
}