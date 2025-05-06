package com.example.workouttracker.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class JwtUtilTest {

    private val jwtUtil = JwtUtil()

    @Test
    fun `generateToken should create a valid token`() {
        val username = "testUser"
        val token = jwtUtil.generateToken(username)

        assertNotNull(token, "El token no debería ser nulo")
        assertTrue(token.isNotEmpty(), "El token no debería estar vacío")
    }

    @Test
    fun `extractUsername should return the correct username`() {
        val username = "testUser"
        val token = jwtUtil.generateToken(username)
        val extractedUsername = jwtUtil.extractUsername(token)

        assertEquals(username, extractedUsername, "El nombre de usuario extraído debería coincidir con el original")
    }

    @Test
    fun `extractUsername should throw exception for invalid token`() {
        val invalidToken = "token_invalido"

        assertThrows(Exception::class.java) {
            jwtUtil.extractUsername(invalidToken)
        }
    }
}
