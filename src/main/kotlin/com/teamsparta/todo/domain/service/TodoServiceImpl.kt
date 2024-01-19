package com.teamsparta.todo.domain.service

import com.teamsparta.todo.domain.dto.*
import com.teamsparta.todo.domain.repository.CommentRepository
import com.teamsparta.todo.domain.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
) : TodoService {

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

    override fun create(todoCreateDto: TodoCreateDto): TodoDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun getAll(): List<TodoDto> {
        return todoRepository.findAll().map { TodoDto(it.id, it.title, it.content, it.authorName) }
    }

    @Transactional
    override fun getById(id: Long): TodoDto {
        val todo = todoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("No todo found with the given id") }
        return TodoDto(todo.id, todo.title, todo.content, todo.authorName)
    }

    override fun update(id: Long, todoUpdateDto: TodoUpdateDto): TodoDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateComment(id: Long, commentUpdateDto: CommentUpdateDto): CommentDto {
        val comment = commentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("No comment found with the given id") }
        comment.content = commentUpdateDto.content
        commentRepository.save(comment)
        return CommentDto.from(comment)
    }

    @Transactional
    override fun delete(id: Long) {
        val todo = todoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("No todo found with the given id") }
        todoRepository.delete(todo)
    }
}