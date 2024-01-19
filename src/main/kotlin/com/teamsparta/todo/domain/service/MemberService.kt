package com.teamsparta.todo.domain.service

import com.teamsparta.todo.domain.dto.MemberCreateDto
import com.teamsparta.todo.domain.dto.MemberDto
import com.teamsparta.todo.domain.dto.MemberUpdateDto
import com.teamsparta.todo.domain.login.LoginRequest
import com.teamsparta.todo.domain.login.LoginResponse

interface MemberService {
    fun signUp(memberCreateDto: MemberCreateDto): MemberDto
    fun login(memberDto: MemberDto): LoginResponse
    fun update(id: Long, memberUpdateDto: MemberUpdateDto): MemberDto
    fun delete(id: Long)
    fun findById(id: Long): MemberDto
    fun updateMemberProfile(id: Long, memberUpdateDto: MemberUpdateDto): MemberDto

}