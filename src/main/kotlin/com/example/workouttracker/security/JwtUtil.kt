package com.example.workouttracker.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class JwtUtil {
    private val secretKey = "your_secret_key"
    private val expirationTime = 86400000 // 1 día en milisegundos
    private val enc = Jwts.ENC.A256GCM //or A128GCM, A192GCM, A256CBC-HS512, etc...
    private val key = enc.key().build()

    fun generateToken(username: String): String {
        val content: ByteArray = username.toByteArray(StandardCharsets.UTF_8)
        return Jwts.builder()
            .content(content, "text/plain")
            .encryptWith(key,enc)
            .compact()
    }

    fun extractUsername(token: String): String {
        return Jwts.parser().decryptWith(key).build().parseEncryptedContent(token).payload.let {
            String(it, StandardCharsets.UTF_8)
        }
    }


}
