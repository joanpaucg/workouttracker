package com.example.workouttracker.service

import com.example.workouttracker.model.User
import com.example.workouttracker.repository.UserRepository
import com.example.workouttracker.security.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtUtil: JwtUtil
) {
    fun register(user: User): String {
        if (userRepository.findByUsername(user.username).isPresent) {
            throw RuntimeException("El usuario ya existe")
        }
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return "Usuario registrado con éxito"
    }

    fun login(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
        if (user.isPresent && passwordEncoder.matches(password, user.get().password)) {
            return jwtUtil.generateToken(username)
        }
        throw RuntimeException("Credenciales inválidas")
    }
}
