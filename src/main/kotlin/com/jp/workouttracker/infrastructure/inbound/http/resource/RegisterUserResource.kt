package com.jp.workouttracker.infrastructure.inbound.http.resource

data class RegisterUserResource(
    val username: String,
    val password: String,
)