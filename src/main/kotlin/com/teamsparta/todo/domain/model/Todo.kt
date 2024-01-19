package com.teamsparta.todo.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Todo")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    val title: String,
    @Column
    val content: String,
    @Column(name = "authorname")
    val authorName: String,
    @Column(name = "createdDate")
    val createdDate: LocalDateTime = LocalDateTime.now(),
    @Column(name = "isCompleted")
    var isCompleted: Boolean = false,
    @OneToMany(mappedBy = "todo")
    val comments: List<Comment> = emptyList(),
)