package com.jp.workouttracker.infrastructure.outbound.db

import com.jp.workouttracker.domain.User
import com.jp.workouttracker.domain.UserRepository
import com.jp.workouttracker.infrastructure.outbound.db.jpa.UserEntity
import com.jp.workouttracker.infrastructure.outbound.db.jpa.UserJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DbUserRepository (val userJpaRepository: UserJpaRepository ) : UserRepository {
    override fun findByUsername(username: String): User? {
        val userEntity = userJpaRepository.findByUsername(username)
        return userEntity?.let {
            User(
                username = it.username,
                password = it.password,
            )
        }
    }
    override fun save(user: User): User {
        val userEntity = UserEntity(
            username = user.username,
            password = user.password,
        )
        val savedUserEntity = userJpaRepository.save(userEntity)
        return User(
            username = savedUserEntity.username,
            password = savedUserEntity.password,
        )
    }

}