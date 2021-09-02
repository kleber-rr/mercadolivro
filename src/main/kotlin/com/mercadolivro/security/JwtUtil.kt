package com.mercadolivro.security

import com.mercadolivro.exceptions.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int): String{
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis().plus(expiration!!)))
            .signWith(SignatureAlgorithm.HS512,secret!!.toByteArray())
            .compact()

    }

    fun isValidToken(token: String): Boolean {
        val claims = getClains(token)
        if(claims.subject == null || claims.expiration == null || Date().after(claims.expiration)){
            return false
        }
        return true
    }

    private fun getClains(token: String): Claims {
        try{
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        } catch (ex: Exception) {
            throw AuthenticationException("Token invalidoaa", "999")
        }
    }

    fun getSubject(token: String): String {
        return getClains(token).subject
    }
}