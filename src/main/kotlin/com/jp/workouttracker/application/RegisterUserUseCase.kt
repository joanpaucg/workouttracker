package com.jp.workouttracker.application

import com.example.workouttracker.model.User
import com.example.workouttracker.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterUserUseCase (@Autowired private val userRepository: UserRepository,
                           @Autowired private val passwordEncoder: PasswordEncoder) {
    fun execute(user: User): String {
        if (userRepository.findByUsername(user.username).isPresent) {
            throw RuntimeException("El usuario ya existe")
        }
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return "Usuario registrado con éxito"
    }
}