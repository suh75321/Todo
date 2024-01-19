package com.teamsparta.todo.domain.model

import jakarta.persistence.*


@Entity
class Member(
    var username: String,
    var password: String,
    var email: String
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun update(username: String, password: String, email: String) {
        this.username = username
        this.password = password
        this.email = email
    }

}
