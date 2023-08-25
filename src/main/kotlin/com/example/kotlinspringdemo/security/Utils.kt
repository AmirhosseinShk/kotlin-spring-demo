package com.example.kotlinspringdemo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom

@Configuration
class Utils() {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        val random = SecureRandom()
        val strength = 10
        return BCryptPasswordEncoder(strength, random)
    }
}