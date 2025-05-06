package com.jp.workouttracker.domain.service

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class ExtractUserNameService {
    private val enc = Jwts.ENC.A256GCM //or A128GCM, A192GCM, A256CBC-HS512, etc...
    private val key = enc.key().build()
    fun extractUsername(token: String): String {
        return Jwts.parser().decryptWith(key).build().parseEncryptedContent(token).payload.let {
            String(it, StandardCharsets.UTF_8)
        }
    }
}