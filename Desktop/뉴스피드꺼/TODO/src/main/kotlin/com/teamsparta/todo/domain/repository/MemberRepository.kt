package com.teamsparta.todo.domain.repository

import com.teamsparta.todo.domain.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Member?
    fun findByUsername(username: String): Member
}