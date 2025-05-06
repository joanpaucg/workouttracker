package com.jp.workouttracker.domain.service

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class GenerateJwtTokenService {
    private val enc = Jwts.ENC.A256GCM //or A128GCM, A192GCM, A256CBC-HS512, etc...
    private val key = enc.key().build()
    fun generateToken(username: String): String {
        val content: ByteArray = username.toByteArray(StandardCharsets.UTF_8)
        return Jwts.builder()
            .content(content, "text/plain")
            .encryptWith(key,enc)
            .compact()
    }
}