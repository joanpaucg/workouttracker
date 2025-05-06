package com.jp.workouttracker.application


import com.jp.workouttracker.domain.User
import com.jp.workouttracker.domain.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterUserUseCase (private val userRepository: UserRepository,
                           private val passwordEncoder: PasswordEncoder) {
    fun execute(username:String,password:String): String {
        if (userRepository.findByUsername(username)!= null) {
            throw RuntimeException("El usuario ya existe")
        }
        val user = User(username = username, password = passwordEncoder.encode(password))
        userRepository.save(user)
        return "Usuario registrado con éxito"
    }
}