package com.jp.workouttracker.infrastructure.outbound.db.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}