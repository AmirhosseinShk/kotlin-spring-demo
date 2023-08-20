package com.example.kotlinspringdemo.security

import com.example.kotlinspringdemo.services.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationFilter(
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val t = getAuthenticationToken(request)
        if (t != "") {
            val user = tokenService.parseToken(t) ?: throw InvalidBearerTokenException("Invalid token")
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(user, "", buildList {
                    add(SimpleGrantedAuthority("USER"))
                })
        }
        doFilter(request, response, filterChain)
    }

    private fun getAuthenticationToken(request: HttpServletRequest): String =
        request.getHeader(HttpHeaders.AUTHORIZATION) ?: ""
}