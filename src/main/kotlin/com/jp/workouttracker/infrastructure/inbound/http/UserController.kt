package com.jp.workouttracker.infrastructure.inbound.http

import com.example.workouttracker.model.User
import com.jp.workouttracker.application.LoginUserUseCase
import com.jp.workouttracker.application.RegisterUserUseCase
import com.jp.workouttracker.infrastructure.inbound.http.resource.RegisterUserResource
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase

) {
    @PostMapping("/register")
    fun register(@RequestBody registerUser: RegisterUserResource): String {
        return registerUserUseCase.execute(
            username = registerUser.username,
            password = registerUser.password
        )
    }
    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): String {
        return loginUserUseCase.execute(username,password)
    }
}