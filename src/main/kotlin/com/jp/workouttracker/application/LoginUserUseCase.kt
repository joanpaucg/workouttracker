package com.jp.workouttracker.application

import com.example.workouttracker.repository.UserRepository
import com.example.workouttracker.security.JwtUtil

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginUserUseCase (@Autowired private val userRepository: UserRepository,
                        @Autowired private val passwordEncoder: PasswordEncoder,
                        @Autowired private val jwtUtil: JwtUtil) {
    fun execute(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
        if (user.isPresent && passwordEncoder.matches(password, user.get().password)) {
            return jwtUtil.generateToken(username)
        }
        throw RuntimeException("Credenciales inválidas")
    }
}