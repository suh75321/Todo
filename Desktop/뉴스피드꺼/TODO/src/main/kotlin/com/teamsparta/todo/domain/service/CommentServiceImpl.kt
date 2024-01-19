package com.teamsparta.todo.domain.service

import com.teamsparta.todo.domain.dto.CommentCreateDto
import com.teamsparta.todo.domain.dto.CommentDeleteDto
import com.teamsparta.todo.domain.dto.CommentDto
import com.teamsparta.todo.domain.dto.CommentUpdateDto
import com.teamsparta.todo.domain.repository.CommentRepository
import com.teamsparta.todo.domain.repository.TodoRepository
import jakarta.transaction.Transactional
import org.hibernate.annotations.Comment
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) : CommentService {

    @Transactional
    override fun createComment(commentCreateDto: CommentCreateDto): CommentDto {
        val todo = todoRepository.findByIdOrNull(commentCreateDto.todoId) ?: throw IllegalArgumentException("Todo not found")
        val comment = com.teamsparta.todo.domain.model.Comment(
            commentCreateDto.content,
            commentCreateDto.authorName,
            commentCreateDto.password,
            todo
        )
        val savedComment = commentRepository.save(comment)
        return CommentDto.from(savedComment)
    }

    @Transactional
    override fun updateComment(commentUpdateDto: CommentUpdateDto): CommentDto {
        val foundComment = commentRepository.findByIdOrNull(commentUpdateDto.id) ?: throw Exception("target comment is not found")
        foundComment.checkAuthentication(commentUpdateDto.authorName, commentUpdateDto.password)
        foundComment.changeContent(commentUpdateDto.content)
        val savedComment = commentRepository.save(foundComment)
        return CommentDto.from(savedComment)
    }

    @Transactional
    override fun deleteComment(commentDeleteDto: CommentDeleteDto) {
        val foundComment = commentDeleteDto.id?.let { commentRepository.findByIdOrNull(it) } ?: throw Exception("target comment is not found")
        foundComment.checkAuthentication(commentDeleteDto.authorName, commentDeleteDto.password)
        commentRepository.deleteById(commentDeleteDto.id)
    }
}