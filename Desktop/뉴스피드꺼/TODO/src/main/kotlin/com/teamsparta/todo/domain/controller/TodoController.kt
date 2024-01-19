package com.teamsparta.todo.domain.controller

import com.teamsparta.todo.domain.dto.TodoCreateDto
import com.teamsparta.todo.domain.dto.TodoDto
import com.teamsparta.todo.domain.dto.TodoUpdateDto
import com.teamsparta.todo.domain.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(private val todoService: TodoService) {

    @PostMapping
    fun create(@RequestBody todoCreateDto: TodoCreateDto): ResponseEntity<TodoDto> {
        val todoDto = todoService.create(todoCreateDto)
        return ResponseEntity.ok(todoDto)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<TodoDto>> {
        val todos = todoService.getAll()
        return ResponseEntity.ok(todos)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TodoDto> {
        val todo = todoService.getById(id)
        return ResponseEntity.ok(todo)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody todoUpdateDto: TodoUpdateDto): ResponseEntity<TodoDto> {
        val updatedTodo = todoService.update(id, todoUpdateDto)
        return ResponseEntity.ok(updatedTodo)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        todoService.delete(id)
        return ResponseEntity.noContent().build()
    }
}