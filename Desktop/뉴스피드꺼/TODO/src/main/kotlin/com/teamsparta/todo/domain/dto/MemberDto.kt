package com.teamsparta.todo.domain.dto

import com.teamsparta.todo.domain.model.Member

data class MemberDto(
    val id: Long?,
    val username: String,
    val password: String,
    val email: String
) {
    companion object {
        fun from(member: Member): MemberDto {
            return MemberDto(member.id, member.username, member.password, member.email)
        }
    }
}