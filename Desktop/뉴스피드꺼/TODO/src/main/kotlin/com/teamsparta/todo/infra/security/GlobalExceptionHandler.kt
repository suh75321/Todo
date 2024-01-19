package com.teamsparta.todo.infra.security

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<com.teamsparta.todo.infra.security.ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleAlreadyAppliedException(e: IllegalStateException): ResponseEntity<com.teamsparta.todo.infra.security.ErrorResponse> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<com.teamsparta.todo.infra.security.ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException): ResponseEntity<com.teamsparta.todo.infra.security.ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(e.message))
    }

}

class ModelNotFoundException(message: String) : RuntimeException(message)

