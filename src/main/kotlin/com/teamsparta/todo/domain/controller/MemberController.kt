package com.teamsparta.todo.domain.controller

import com.teamsparta.todo.domain.dto.MemberCreateDto
import com.teamsparta.todo.domain.dto.MemberDto
import com.teamsparta.todo.domain.dto.MemberUpdateDto
import com.teamsparta.todo.domain.login.LoginResponse
import com.teamsparta.todo.domain.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody memberCreateDto: MemberCreateDto): ResponseEntity<MemberDto> {
        val memberDto = memberService.signUp(memberCreateDto)
        return ResponseEntity.ok(memberDto)
    }
    @PostMapping("/login")
    fun login(@RequestBody memberDto: MemberDto): ResponseEntity<LoginResponse> {
        val token = memberService.login(memberDto)
        return ResponseEntity.ok(token)
    }


    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): ResponseEntity<MemberDto> {
        val memberDto = memberService.findById(id)
        return ResponseEntity.ok(memberDto)
    }

    @PutMapping("/{id}")
    fun updateMember(@PathVariable id: Long, @RequestBody memberUpdateDto: MemberUpdateDto): ResponseEntity<MemberDto> {
        val updatedMemberDto = memberService.update(id, memberUpdateDto)
        return ResponseEntity.ok(updatedMemberDto)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long): ResponseEntity<Void> {
        memberService.delete(id)
        return ResponseEntity.noContent().build()
    }
}