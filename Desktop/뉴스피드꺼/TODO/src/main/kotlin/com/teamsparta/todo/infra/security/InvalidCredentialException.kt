package com.teamsparta.todo.infra.security

data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()