package com.teamsparta.todo.domain.controller

import com.teamsparta.todo.domain.dto.CommentCreateDto
import com.teamsparta.todo.domain.dto.CommentDeleteDto
import com.teamsparta.todo.domain.dto.CommentDto
import com.teamsparta.todo.domain.dto.CommentUpdateDto
import com.teamsparta.todo.domain.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun create(@RequestBody commentCreateDto: CommentCreateDto): ResponseEntity<CommentDto> {
        val commentDto = commentService.createComment(commentCreateDto)  // 메소드 이름 변경
        return ResponseEntity.ok(commentDto)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody commentUpdateDto: CommentUpdateDto): ResponseEntity<CommentDto> {
        val updatedComment = commentService.updateComment(commentUpdateDto)  // 메소드 이름 변경
        return ResponseEntity.ok(updatedComment)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, @RequestBody commentDeleteDto: CommentDeleteDto): ResponseEntity<Void> {
        commentService.deleteComment(commentDeleteDto)  // 메소드 이름 변경
        return ResponseEntity.noContent().build()
    }
}