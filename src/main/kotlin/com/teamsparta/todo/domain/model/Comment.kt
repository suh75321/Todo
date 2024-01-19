package com.teamsparta.todo.domain.model

import jakarta.persistence.*

@Entity
class Comment(
    var content: String,
    var authorName: String,
    var password: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    var todo: Todo
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun checkAuthentication(authorName: String, password: String) {
        if (this.authorName != authorName || this.password != password) {
            throw IllegalArgumentException("Authentication failed")
        }
    }

    fun changeContent(content: String) {
        this.content = content
    }
}