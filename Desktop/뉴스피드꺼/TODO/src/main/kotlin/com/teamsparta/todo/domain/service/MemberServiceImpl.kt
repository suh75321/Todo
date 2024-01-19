package com.teamsparta.todo.domain.service

import com.teamsparta.todo.domain.dto.MemberCreateDto
import com.teamsparta.todo.domain.dto.MemberDto
import com.teamsparta.todo.domain.dto.MemberUpdateDto
import com.teamsparta.todo.domain.login.LoginResponse
import com.teamsparta.todo.domain.model.Member
import com.teamsparta.todo.domain.repository.MemberRepository
import com.teamsparta.todo.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : MemberService {

    @Transactional
    override fun signUp(memberCreateDto: MemberCreateDto): MemberDto {
        if (memberRepository.existsByEmail(memberCreateDto.email)) {
            throw IllegalStateException("Email is already in use")
        }

        val encryptedPassword = passwordEncoder.encode(memberCreateDto.password)
        val member = Member(memberCreateDto.username, memberCreateDto.email, encryptedPassword)
        val savedMember = memberRepository.save(member)
        return MemberDto.from(savedMember)

    }

    @Transactional
    override fun login(memberDto: MemberDto): LoginResponse {
        val member = memberRepository.findByEmail(memberDto.email)
            ?: throw IllegalArgumentException("No member found with the given email")

        if (!passwordEncoder.matches(memberDto.password, member.password)) {
            throw IllegalArgumentException("Invalid password")
        }

        val token = jwtPlugin.generateToken(member.id.toString(), member.email, java.time.Duration.ofHours(jwtPlugin.accessTokenExpirationHour.toLong()))
        return LoginResponse(token)
    }

    @Transactional
    override fun updateMemberProfile(id: Long, memberUpdateDto: MemberUpdateDto): MemberDto {
        val member = memberRepository.findById(id).orElseThrow { IllegalArgumentException("Member not found") }

        if(memberUpdateDto.password != null) {
            member.password = passwordEncoder.encode(memberUpdateDto.password)
        }

        member.username = memberUpdateDto.username ?: member.username
        member.email = memberUpdateDto.email ?: member.email

        val updatedMember = memberRepository.save(member)
        return MemberDto.from(updatedMember)
    }

    @Transactional
    override fun update(id: Long, memberUpdateDto: MemberUpdateDto): MemberDto {
        val member = memberRepository.findById(id).orElseThrow { IllegalArgumentException("Member not found") }
        member.update(memberUpdateDto.username, memberUpdateDto.password, memberUpdateDto.email)
        val updatedMember = memberRepository.save(member)
        return MemberDto.from(updatedMember)
    }

    @Transactional
    override fun delete(id: Long) {
        val member = memberRepository.findById(id)
            .orElseThrow { IllegalArgumentException("No member found with the given id") }

        memberRepository.delete(member)
    }
    @Transactional
    override fun findById(id: Long): MemberDto {
        val member = memberRepository.findById(id).orElseThrow { IllegalArgumentException("Member not found") }
        return MemberDto.from(member)
    }
}