package com.example.kotlinspringdemo.security

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtEncodingConfig(@Value("\${security.api-token-key}") private val apiTokenSecurityKey: String) {
    private val apiTokenSecretKey = SecretKeySpec(apiTokenSecurityKey.toByteArray(), "HmacSHA256")

    @Bean(name = ["jwt_decoder"])
    fun apiTokenJwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withSecretKey(apiTokenSecretKey).build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val secret = ImmutableSecret<SecurityContext>(apiTokenSecretKey)
        return NimbusJwtEncoder(secret)
    }

}