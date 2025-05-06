package com.example.workouttracker.controller

import com.example.workouttracker.model.User
import com.example.workouttracker.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    @Autowired private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(@RequestBody user: User): String {
        return authService.register(user)
    }

    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): String {
        return authService.login(username, password)
    }
}
