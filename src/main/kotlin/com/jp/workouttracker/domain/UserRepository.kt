package com.jp.workouttracker.domain

interface UserRepository {
    fun findByUsername(username:String): User?
    fun save(user: User): User
}