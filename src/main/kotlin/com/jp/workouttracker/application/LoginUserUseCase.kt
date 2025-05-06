package com.jp.workouttracker.application
import com.example.workouttracker.security.JwtUtil
import com.jp.workouttracker.domain.UserRepository
import com.jp.workouttracker.domain.service.GenerateJwtTokenService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class LoginUserUseCase (private val userRepository: UserRepository,
                        private val passwordEncoder: PasswordEncoder,
                        private val generateJwtTokenService: GenerateJwtTokenService) {
    fun execute(username: String, password: String): String {
        val user = userRepository.findByUsername(username)?.takeIf { passwordEncoder.matches(password, it.password) }
            ?: throw RuntimeException("Credenciales inválidas")
        return generateJwtTokenService.generateToken(username)
    }
}